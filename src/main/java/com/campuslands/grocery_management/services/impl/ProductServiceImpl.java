package com.campuslands.grocery_management.services.impl;

import com.campuslands.grocery_management.dtos.ProductDto;
import com.campuslands.grocery_management.exceptions.ProductAlreadyExistsException;
import com.campuslands.grocery_management.exceptions.ProductNotFountException;
import com.campuslands.grocery_management.mappers.ProductMapper;
import com.campuslands.grocery_management.models.Product;
import com.campuslands.grocery_management.repositories.ProductRepository;
import com.campuslands.grocery_management.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        boolean existProduct = productRepository.existsByName(productDto.getName());
        if (existProduct){
            throw new ProductAlreadyExistsException("The product already exists with a name: "+productDto.getName());
        }
        Product product = productMapper.toProduct(productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getProducts() {
        return productMapper.toProductsDto(productRepository.findAll());
    }

    @Override
    public ProductDto findByProductId(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFountException("Product not fount with: "+productId));
        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFountException("Product not fount with: "+productId));
        productMapper.updateProduct(product, productDto);
        return productMapper.toProductDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFountException("Product not fount with: "+productId));
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> findByCategory(String categoryName) {
        return productMapper.toProductsDto(productRepository.findByCategory(categoryName));
    }
}
