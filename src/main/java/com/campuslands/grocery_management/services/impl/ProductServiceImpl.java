package com.campuslands.grocery_management.services.impl;

import com.campuslands.grocery_management.dtos.ProductDto;
import com.campuslands.grocery_management.exceptions.ProductAlreadyExistsException;
import com.campuslands.grocery_management.exceptions.ProductNotFountException;
import com.campuslands.grocery_management.mappers.ProductMapper;
import com.campuslands.grocery_management.models.Product;
import com.campuslands.grocery_management.repositories.ProductRepository;
import com.campuslands.grocery_management.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    public ProductDto saveProduct(ProductDto productDto) {

        boolean existProduct = productRepository.existsBySKU(productDto.getSKU());
        if (existProduct){
            logger.warn("Attempt to save duplicated product with SKU: {}", productDto.getSKU());
            throw new ProductAlreadyExistsException("The product already exists with SKU: "+productDto.getSKU());
        }
        Product product = productMapper.toProduct(productDto);
        Product saveProduct = productRepository.save(product);
        logger.info("Product save success: {}",saveProduct);
        return productMapper.toProductDto(saveProduct);
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
        Product updateProduct = productRepository.save(product);
        logger.info("Product update success with id: {}", updateProduct.getProductId());
        return productMapper.toProductDto(updateProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFountException("Product not fount with: "+productId));
        logger.info("Product delete success with id: {}", product.getProductId());
        productRepository.delete(product);
    }

    @Override
    public List<ProductDto> findByCategory(String categoryName) {
        return productMapper.toProductsDto(productRepository.findByCategory(categoryName));
    }
}
