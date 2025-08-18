package com.ecommerce.mmstechnology.ecommerce_application.dto.request;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import com.ecommerce.mmstechnology.ecommerce_application.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * UserDtoRequest.java
 * Project: ecommerce-application, Created by M on 7/8/2025.
 * © 2025 mmstechnology
 */
@Data
public class UserDtoRequest {


	private Long userId;
	private String firstName;
	private String lastName;

	private String email;
	private Long phoneNumber;

	private AddressDto addressDto;
}
