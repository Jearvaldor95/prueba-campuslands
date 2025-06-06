package com.campuslands.grocery_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "sku", unique = true)
    private String SKU;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false)
    private String category;
}
