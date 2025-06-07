package com.campuslands.grocery_management;

import com.campuslands.grocery_management.dtos.SaleDto;
import com.campuslands.grocery_management.dtos.SaleRequest;
import com.campuslands.grocery_management.mappers.SaleMapper;
import com.campuslands.grocery_management.models.Product;
import com.campuslands.grocery_management.models.Sale;
import com.campuslands.grocery_management.repositories.ProductRepository;
import com.campuslands.grocery_management.repositories.SaleRepository;
import com.campuslands.grocery_management.services.impl.SaleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@DisplayName("Test sales service")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SalesServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;
    @Mock
    private SaleRepository saleRepository;
    @Mock
    private SaleMapper saleMapper;
    private SaleDto saleDto;
    private Sale sale;
    Long saleId = 1L;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void init() {
        product = Product.builder()
                .productId(1L)
                .name("Arroz")
                .SKU("ARZ001")
                .category("Granos")
                .stock(100)
                .unitPrice(2000.0)
                .build();

        saleDto = SaleDto.builder()
                .saleId(saleId)
                .customerIdentifier("12344")
                .productId(1L)
                .quantityPurchased(3)
                .totalAmountSale(6000.0)
                .build();

        sale = Sale.builder()
                .saleId(saleId)
                .customerIdentifier("12344")
                .product(product)
                .quantityPurchased(3)
                .totalAmountSale(6000.0)
                .saleDate(LocalDate.now())
                .build();
    }




    @DisplayName("Test to save sale")
    @Test
    public void saveSale() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(saleMapper.toSale(saleDto)).thenReturn(sale);
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);
        when(saleMapper.toSaleDto(sale)).thenReturn(saleDto);

        SaleDto result = saleService.saveSale(saleDto);

        assertNotNull(result);
        assertEquals(6000.0, result.getTotalAmountSale());
    }

    @DisplayName("Test get all sales")
    @Test
    public void getSales() {
        when(saleRepository.findAll()).thenReturn(Collections.singletonList(sale));
        when(saleMapper.toSalesDto(anyList())).thenReturn(Collections.singletonList(saleDto));

        List<SaleDto> sales = saleService.getSales();

        assertNotNull(saleService.getSales());
        assertEquals(6000.0, sales.get(0).getTotalAmountSale());

    }

    @DisplayName("Test get all sales by customerIdentifier")
    @Test
    public void getSalesByCustomerIdentifier() {
        when(saleRepository.findByCustomerIdentifier("12344")).thenReturn(Collections.singletonList(sale));
        when(saleMapper.toSalesDto(anyList())).thenReturn(Collections.singletonList(saleDto));

        List<SaleDto> sales = saleService.findByCustomerIdentifier("12344");

        assertNotNull(saleService.getSales());

        assertNotNull(sales);
        assertEquals(3, sales.get(0).getQuantityPurchased());;
        assertEquals(6000.0, sales.get(0).getTotalAmountSale());;
    }



}
