package com.ecommerce.mmstechnology.ecommerce_application.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/*
 * ProductDtoResponse.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Data
public class ProductResponseDto {
	private Long productId;

	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private String category;
	private String imageUrl;
	private Boolean active = true;
}
