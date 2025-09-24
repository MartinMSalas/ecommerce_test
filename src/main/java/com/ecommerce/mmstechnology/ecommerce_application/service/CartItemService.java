package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.CartItemRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.CartItemResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ProductNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ProductWithoutStockNeeded;
import com.ecommerce.mmstechnology.ecommerce_application.exception.UserNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.CartItemMapper;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.ProductMapper;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.UserMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.CartItem;
import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import com.ecommerce.mmstechnology.ecommerce_application.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

    private final IProductService productService;
    private final ProductMapper productMapper;

    private final IUserService userService;
    private final UserMapper userMapper;

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public Optional<CartItemResponseDto> addToCart(Long userId, CartItemRequestDto cartItemRequestDto) {

        log.debug("Attempting to add Product with id: {} to cart of userId: {}",cartItemRequestDto.getProductId(), userId);
        Optional<UserResponseDto> userResponseDto = userService.getUserById(userId);
        // Look for User
        if(userResponseDto.isEmpty()){
            throw new UserNotFoundException("User not found with id " + userId);
        }

        // Look for product
        Optional<ProductResponseDto> productResponseDto = productService.getProductById(cartItemRequestDto.getProductId());
        if(productResponseDto.isEmpty()){
            throw new ProductNotFoundException("Product not found with id " + cartItemRequestDto.getProductId());
        } else if(productResponseDto.get().getStockQuantity()< cartItemRequestDto.getQuantity()){
            throw new ProductWithoutStockNeeded("Product without the stock quantity needed");
        }

        Optional<CartItem> cartItem = cartItemRepository.findByUserAndProduct(userMapper.toUserFromResponse(userResponseDto.get())
                ,productMapper.toProductFromResponse(productResponseDto.get()));

        if(cartItem.isPresent()){
            // Update the quantity
            CartItem cartItemFound = cartItem.get();
            cartItemFound.setQuantity(cartItemFound.getQuantity() + cartItemRequestDto.getQuantity() );
            cartItemFound.setPrice(productResponseDto.get().getPrice().multiply(BigDecimal.valueOf(cartItemFound.getQuantity())));
            return Optional.of(cartItemMapper.toDto(cartItemFound));
        } else {
            // Create new cart item
            CartItem cartItemCreated = new CartItem();
            cartItemCreated.setUser(userMapper.toUserFromResponse(userResponseDto.get()));
            cartItemCreated.setProduct(productMapper.toProductFromResponse(productResponseDto.get()));
            cartItemCreated.setQuantity(cartItemRequestDto.getQuantity());
            cartItemCreated.setPrice(productResponseDto.get().getPrice().multiply(BigDecimal.valueOf(cartItemRequestDto.getQuantity())));
            return Optional.of(cartItemMapper.toDto(cartItemRepository.save(cartItemCreated)));
        }


    }
}

