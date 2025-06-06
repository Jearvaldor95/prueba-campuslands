package com.campuslands.grocery_management;

import com.campuslands.grocery_management.dtos.SaleDto;
import com.campuslands.grocery_management.services.SaleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Sql("/data/test_data.sql")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SaleServiceIntegrationTest {

    @Autowired
    private SaleService saleService;

    @DisplayName("Save a sale and discount product stock")
    @Test
    public void testSaveSale() {
        SaleDto saleDto = SaleDto.builder()
                .customerIdentifier("123456789")
                .productId(1L) // el producto con ID 1 está en data.sql
                .quantityPurchased(2)
                .build();

        SaleDto result = saleService.saveSale(saleDto);

        assertNotNull(result);
        assertEquals(2, result.getQuantityPurchased());
        assertEquals(5000.0, result.getTotalAmountSale());
        assertEquals(1L, result.getProductId());
    }
}
