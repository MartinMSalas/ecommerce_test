package com.ecommerce.mmstechnology.ecommerce_application.repository;

import com.ecommerce.mmstechnology.ecommerce_application.model.CartItem;
import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem,Long> {

    Optional<CartItem> findByUserAndProduct(User user, Product product);

}
