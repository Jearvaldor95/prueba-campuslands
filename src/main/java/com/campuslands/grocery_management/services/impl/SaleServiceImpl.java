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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper, ProductRepository productRepository){
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
        this.productRepository = productRepository;
    }

    public double existsProduct(SaleDto saleDto){
        Product product = productRepository.findById(saleDto.getProductId())
                .orElseThrow(() -> new ProductNotFountException("Product not fount with: "+ saleDto.getProductId()));

        if (product.getStock() < saleDto.getQuantityPurchased()) {
            logger.warn("Attempt add insufficient stock for the product: {}",product.getName());
            throw new BadRequestException("Insufficient stock for the product: " + product.getName());
        }
        double totalSale = product.getUnitPrice() * saleDto.getQuantityPurchased();

        product.setStock(product.getStock() - saleDto.getQuantityPurchased());
        productRepository.save(product);
        logger.info("Stock discount for the product: {}", product.getName());
        return totalSale;
    }
    @Override
    public SaleDto saveSale(SaleDto saleDto) {

        double totalSale = existsProduct(saleDto);

        Sale sale = saleMapper.toSale(saleDto);
        sale.setTotalAmountSale(totalSale);

        LocalDate hoy = LocalDate.now();
        sale.setSaleDate(hoy);
        Sale saveSale = saleRepository.save(sale);
        logger.info("Save sale success: {}", saveSale);
        return saleMapper.toSaleDto(saveSale);
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
