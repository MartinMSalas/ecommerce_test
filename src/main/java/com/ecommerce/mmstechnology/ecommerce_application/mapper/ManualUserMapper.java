package com.ecommerce.mmstechnology.ecommerce_application.mapper;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.Address;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.model.UserRole;

/*
 * ManualUserMapper.java
 * Project: ecommerce-application, Created by M on 17/8/2025.
 * © 2025 mmstechnology
 */
public class ManualUserMapper {

    public static UserDtoResponse userDtoResponse(User user){
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        if(user.getUserId() != null)
            userDtoResponse.setUserId(user.getUserId());
        if(user.getFirstName() != null) 
            userDtoResponse.setFirstName(user.getFirstName());
        if(user.getLastName() != null)
            userDtoResponse.setLastName(user.getLastName());
        if(user.getEmail() != null)
            userDtoResponse.setEmail(user.getEmail());
        if(user.getUserRole() != null)
            userDtoResponse.setUserRole(user.getUserRole());
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

            userDtoResponse.setAddressDto(addressDto);
        }
        return userDtoResponse;
          
    }
}
