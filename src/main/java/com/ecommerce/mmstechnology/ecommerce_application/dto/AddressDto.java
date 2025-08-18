package com.ecommerce.mmstechnology.ecommerce_application.dto;

import lombok.Data;

/*
 * AddressDto.java
 * Project: ecommerce-application, Created by M on 17/8/2025.
 * © 2025 mmstechnology
 */
@Data
public class AddressDto {


	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}
