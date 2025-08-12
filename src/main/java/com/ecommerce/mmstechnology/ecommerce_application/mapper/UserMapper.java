package com.ecommerce.mmstechnology.ecommerce_application.mapper;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
 * UserMapper.java
 * Project: ecommerce-application, Created by M on 9/8/2025.
 * © 2025 mmstechnology
 */
@Mapper(componentModel = "spring")
public interface UserMapper {



	UserDtoResponse toDto(User user);

	User toUser(UserDtoRequest userDtoRequest);
	List<UserDtoResponse> toDtoList(List<User> users);
}
