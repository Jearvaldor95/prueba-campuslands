package com.campuslands.grocery_management.controllers;

import com.campuslands.grocery_management.dtos.SaleDto;
import com.campuslands.grocery_management.dtos.SaleRequest;
import com.campuslands.grocery_management.mappers.SaleMapper;
import com.campuslands.grocery_management.services.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SaleController {

    private final SaleService saleService;
    private final SaleMapper saleMapper;

    public SaleController(SaleService saleService, SaleMapper saleMapper){
        this.saleService = saleService;
        this.saleMapper = saleMapper;
    }

    @PostMapping
    public ResponseEntity<SaleDto> saveSale(@RequestBody SaleRequest saleRequest){
        SaleDto saleDto = saleMapper.toSaleDto(saleRequest);
        return new ResponseEntity<>(saleService.saveSale(saleDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SaleDto>> getSales(){
        return new ResponseEntity<>(saleService.getSales(), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerIdentifier}")
    public ResponseEntity<List<SaleDto>> findByCustomerIdentifier(@PathVariable String customerIdentifier){
        return new ResponseEntity<>(saleService.findByCustomerIdentifier(customerIdentifier), HttpStatus.OK);
    }
}
