package com.campuslands.grocery_management.services;

import com.campuslands.grocery_management.dtos.SaleDto;

import java.util.List;

public interface SaleService {

    SaleDto saveSale(SaleDto saleDto);

    List<SaleDto> getSales();

    List<SaleDto> findByCustomerIdentifier(String customerIdentifier);
}
