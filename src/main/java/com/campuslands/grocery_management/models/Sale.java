package com.campuslands.grocery_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleId;
    @Column(nullable = false)
    private String customerIdentifier;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantityPurchased;
    @Column(nullable = false)
    @CreatedDate
    private LocalDate saleDate = LocalDate.now();
    @Column(nullable = false)
    private Double totalAmountSale;

}
