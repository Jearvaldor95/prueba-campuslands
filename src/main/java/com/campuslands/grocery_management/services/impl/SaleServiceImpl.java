package com.campuslands.grocery_management.services.impl;

import com.campuslands.grocery_management.dtos.SaleDto;
import com.campuslands.grocery_management.exceptions.BadRequestException;
import com.campuslands.grocery_management.exceptions.ProductNotFountException;
import com.campuslands.grocery_management.mappers.SaleMapper;
import com.campuslands.grocery_management.models.Product;
import com.campuslands.grocery_management.models.Sale;
import com.campuslands.grocery_management.repositories.ProductRepository;
import com.campuslands.grocery_management.repositories.SaleRepository;
import com.campuslands.grocery_management.services.SaleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductRepository productRepository;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper, ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
    }

    public double existsProduct(SaleDto saleDto){
        Product product = productRepository.findById(saleDto.getProductId())
                .orElseThrow(() -> new ProductNotFountException("Product not fount with: "+ saleDto.getProductId()));

        if (product.getStock() < saleDto.getQuantityPurchased()) {
            throw new BadRequestException("Insufficient stock for the product: " + product.getName());
        }
        double totalSale = product.getUnitPrice() * saleDto.getQuantityPurchased();

        product.setStock(product.getStock() - saleDto.getQuantityPurchased());
        productRepository.save(product);
        return totalSale;
    }
    @Override
    public SaleDto saveSale(SaleDto saleDto) {

        double totalSale = existsProduct(saleDto);

        Sale sale = saleMapper.toSale(saleDto);
        sale.setTotalAmountSale(totalSale);

        LocalDate hoy = LocalDate.now();
        sale.setSaleDate(hoy);
        return saleMapper.toSaleDto(saleRepository.save(sale));
    }

    @Override
    public List<SaleDto> getSales() {
        return saleMapper.toSalesDto(saleRepository.findAll());
    }

    @Override
    public List<SaleDto> findByCustomerIdentifier(String customerIdentifier) {
        return saleMapper.toSalesDto(saleRepository.findByCustomerIdentifier(customerIdentifier));
    }
}
