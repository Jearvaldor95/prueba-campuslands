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
public class SaleRequest {

    @NotBlank(message = "Customer identifier is required")
    private String customerIdentifier;
    @NotNull(message = "ProductId is required")
    private Long productId;
    @NotNull(message = "Quantity purchased is required")
    private Integer quantityPurchased;
}
