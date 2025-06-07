package com.campuslands.grocery_management.repositories;

import com.campuslands.grocery_management.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySKU(String SKU);
    List<Product> findByCategory(String category);
}
