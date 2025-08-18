package com.ecommerce.mmstechnology.ecommerce_application.mapper;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
 * UserMapper.java
 * Project: ecommerce-application, Created by M on 9/8/2025.
 * © 2025 mmstechnology
 */
@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface UserMapper {


	@Mapping(target = "addressDto", source = "address")  // <— connect different names/types
	UserDtoResponse toDto(User user);
	@Mapping(target = "address", source = "addressDto")  // <— connect different names/types
	User toUser(UserDtoRequest userDtoRequest);
	List<UserDtoResponse> toDtoList(List<User> users);


	// Optional: for PATCH-like updates without wiping non-provided fields
//	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//	@Mapping(target = "address", source = "addressDto")
//	void updateUserFromDto(UserDtoRequest dto, @MappingTarget User user);
}
