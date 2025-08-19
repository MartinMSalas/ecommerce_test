package com.ecommerce.mmstechnology.ecommerce_application.repository;

import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * ProductRepository.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
