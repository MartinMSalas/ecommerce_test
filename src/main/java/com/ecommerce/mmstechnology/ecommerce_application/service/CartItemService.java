package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.CartItemRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.CartItemResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ProductNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.exception.UserNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.ProductMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import com.ecommerce.mmstechnology.ecommerce_application.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class CartItemService implements ICartItemService {

    private final IProductService productService;
    private final ProductMapper productMapper;
    private final IUserService userService;
    private final CartItemRepository cartItemRepository;

    @Override
    public Optional<CartItemResponseDto> addToCart(Long userId, CartItemRequestDto cartItemRequestDto) {
        log.debug("Attempting to add Product with id: {} to cart of userId: {}",cartItemRequestDto.getProductId(), userId);
        // Look for product
        if(productService.getProductById(cartItemRequestDto.getProductId()).isEmpty()){
            throw new ProductNotFoundException("Product not found with id " + cartItemRequestDto.getProductId());
        }
        // Look for User
        if(userService.getUserById(userId).isEmpty()){
            throw new UserNotFoundException("User not found with id " + userId);
        }





        return Optional.empty();
    }
}
