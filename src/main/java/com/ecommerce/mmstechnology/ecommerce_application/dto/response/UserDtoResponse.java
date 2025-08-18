package com.ecommerce.mmstechnology.ecommerce_application.dto.response;

import com.ecommerce.mmstechnology.ecommerce_application.dto.AddressDto;
import com.ecommerce.mmstechnology.ecommerce_application.model.UserRole;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * UserDTOResponse.java
 * Project: ecommerce-application, Created by M on 7/8/2025.
 * © 2025 mmstechnology
 */
@Data
public class UserDtoResponse {


	private Long userId;
	private String firstName;
	private String lastName;

	private String email;
	private Long phoneNumber;

	private UserRole userRole = UserRole.CUSTOMER;

	private AddressDto addressDto;




}
