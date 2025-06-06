package com.campuslands.grocery_management.repositories;

import com.campuslands.grocery_management.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerIdentifier(String customerIdentifier);
}
