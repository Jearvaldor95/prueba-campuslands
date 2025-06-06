package com.campuslands.grocery_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {

    private Long productId;
    @NotBlank(message = "SKU is required")
    private String SKU;
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Unit price is required")
    private Double unitPrice;
    @NotNull(message = "Stock is required")
    private Integer stock;
    @NotBlank(message = "Name category is required")
    private String category;
}
