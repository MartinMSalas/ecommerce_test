package com.ecommerce.mmstechnology.ecommerce_application.mapper;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.CartItemRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.CartItemResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.model.CartItem;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface CartItemMapper {


        CartItemResponseDto toDto(CartItem cartItem);

        CartItem toCartItem(CartItemRequestDto cartItemRequestDto);


}
