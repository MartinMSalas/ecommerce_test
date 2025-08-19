package com.ecommerce.mmstechnology.ecommerce_application.dto.request;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import lombok.Data;

/*
 * UserDtoRequest.java
 * Project: ecommerce-application, Created by M on 7/8/2025.
 * © 2025 mmstechnology
 */
@Data
public class UserRequestDto {


	private Long userId;
	private String firstName;
	private String lastName;

	private String email;
	private Long phoneNumber;

	private AddressDto addressDto;
}
