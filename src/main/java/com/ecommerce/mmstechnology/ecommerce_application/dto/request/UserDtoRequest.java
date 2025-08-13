package com.ecommerce.mmstechnology.ecommerce_application.dto.request;

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
	@NotBlank(message = "First name cannot be blank")
	@Size(max = 50, message = "First name cannot exceed 50 characters")
	private String firstName;
	private String lastName;
}
