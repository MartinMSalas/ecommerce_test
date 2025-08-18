package com.ecommerce.mmstechnology.ecommerce_application.mapper;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.Address;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/*
 * AddressMapper.java
 * Project: ecommerce-application, Created by M on 17/8/2025.
 * © 2025 mmstechnology
 */

@Mapper(componentModel = "spring")
public interface AddressMapper {

	AddressDto toDto(Address address);

	Address toEntity(AddressDto addressDto);
}
