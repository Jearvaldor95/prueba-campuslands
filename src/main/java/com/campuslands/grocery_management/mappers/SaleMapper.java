package com.campuslands.grocery_management.mappers;

import com.campuslands.grocery_management.dtos.SaleDto;
import com.campuslands.grocery_management.dtos.SaleRequest;
import com.campuslands.grocery_management.models.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleMapper {


    @Mapping(target = "product.productId", source= "productId")
    Sale toSale(SaleDto saleDto);

    @Mapping(target = "productId", source = "product.productId")
    SaleDto toSaleDto(Sale sale);

    List<SaleDto> toSalesDto(List<Sale> sales);

    @Mappings({
            @Mapping(target = "saleId", ignore = true),
            @Mapping(target = "customerIdentifier", ignore = true),
            @Mapping(target = "product", ignore = true)

    })
    void updateSale(@MappingTarget Sale sale, SaleDto saleDto);


    SaleDto toSaleDto(SaleRequest saleRequest);
}
