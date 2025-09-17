package com.ecommerce.mmstechnology.ecommerce_application.dto.request;

import lombok.Data;

@Data
public class CartItemRequestDto {

    private Long productId;
    private Integer quantity;
}
