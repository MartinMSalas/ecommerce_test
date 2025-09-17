package com.ecommerce.mmstechnology.ecommerce_application.controller;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.CartItemRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.CartItemResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ResourceNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.service.ICartItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/va/cart")
@AllArgsConstructor
@Slf4j
public class CartController {

    final ICartItemService cartItemService;


    @PostMapping
    public ResponseEntity<CartItemResponseDto> addToCart(@RequestHeader("X-User-ID") Long userId,
                                                         @RequestBody CartItemRequestDto cartItemRequestDto){
        log.info("Request to add product to cart");
        return cartItemService.addToCart(userId, cartItemRequestDto)
                    .map(cartItemResponseDto ->
                        new ResponseEntity<>(cartItemResponseDto, HttpStatus.OK)   )
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

    }



}
