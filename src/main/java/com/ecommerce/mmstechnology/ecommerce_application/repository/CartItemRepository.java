package com.ecommerce.mmstechnology.ecommerce_application.repository;

import com.ecommerce.mmstechnology.ecommerce_application.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {

    Optional<CartItem> findByUserAndProduct(Long userId, Long productId);

}
