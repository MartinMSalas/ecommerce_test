package com.ecommerce.mmstechnology.ecommerce_application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Address.java
 * Project: ecommerce-application, Created by M on 17/8/2025.
 * © 2025 mmstechnology
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}
