package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.CartItemRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.CartItemResponseDto;

import java.util.Optional;

public interface ICartItemService {

    Optional<CartItemResponseDto> addToCart(Long userId, CartItemRequestDto cartItemRequestDto);
}
