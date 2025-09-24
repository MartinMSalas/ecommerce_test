package com.ecommerce.mmstechnology.ecommerce_application.dto.response;

import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CartItemResponseDto {

    private Long id;

    private User user;
    private Product product;

    private Integer quantity;
    private BigDecimal price;

}
