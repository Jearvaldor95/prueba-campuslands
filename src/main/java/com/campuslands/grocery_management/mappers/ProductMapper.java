package com.campuslands.grocery_management.mappers;

import com.campuslands.grocery_management.dtos.ProductDto;
import com.campuslands.grocery_management.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductsDto(List<Product> products);

    @Mapping(target = "productId", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductDto productDto);

}
