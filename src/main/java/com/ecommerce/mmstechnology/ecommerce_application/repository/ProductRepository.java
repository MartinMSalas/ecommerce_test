package com.ecommerce.mmstechnology.ecommerce_application.repository;

import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * ProductRepository.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByActiveTrue();

    @Query("SELECT p FROM products p WHERE p.active = true AND p.stockQuantity > 0 AND LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}
