package com.campuslands.grocery_management.services;

import com.campuslands.grocery_management.dtos.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> getProducts();

    ProductDto findByProductId(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

    List<ProductDto> findByCategory(String categoryName);
}
