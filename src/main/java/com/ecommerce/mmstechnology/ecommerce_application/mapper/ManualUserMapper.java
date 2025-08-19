package com.ecommerce.mmstechnology.ecommerce_application.mapper;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.model.Address;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;

/*
 * ManualUserMapper.java
 * Project: ecommerce-application, Created by M on 17/8/2025.
 * © 2025 mmstechnology
 */
public class ManualUserMapper {

    public static UserResponseDto userDtoResponse(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        if(user.getUserId() != null)
            userResponseDto.setUserId(user.getUserId());
        if(user.getFirstName() != null) 
            userResponseDto.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            userResponseDto.setLastName(user.getLastName());
        if(user.getEmail() != null)
            userResponseDto.setEmail(user.getEmail());
        if(user.getUserRole() != null)
            userResponseDto.setUserRole(user.getUserRole());
        if(user.getAddress() != null){
            AddressDto addressDto = new AddressDto();
            Address address = user.getAddress();
            if(address.getCity() != null)
                addressDto.setCity(address.getCity());
            if(address.getCountry() != null)
                addressDto.setCountry(address.getCountry());
            if(address.getState() != null)
                addressDto.setState(address.getState());
            if(address.getStreet() != null)
                addressDto.setStreet(address.getStreet());
            if(address.getZipCode() != null)
                addressDto.setZipCode(address.getZipCode());

            userResponseDto.setAddressDto(addressDto);
        }
        return userResponseDto;
          
    }
}
