package com.campuslands.grocery_management.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaleDto {

    private Long saleId;
    @NotBlank(message = "Customer identifier is required")
    private String customerIdentifier;
    @NotNull(message = "ProductId is required")
    private Long product;
    @NotNull(message = "Quantity purchased is required")
    private Integer quantityPurchased;
    private LocalDate saleDate;
    private Double totalAmountSale;
}
