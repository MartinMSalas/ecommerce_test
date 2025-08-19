package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * ProductService.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Service
public class ProductService implements IProductService {

	@Override
	public Optional<ProductResponseDto> getProductById(Long id) {
		return Optional.empty();
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		return List.of();
	}

	@Override
	public Optional<ProductResponseDto> createProduct(ProductRequestDto user) {
		return Optional.empty();
	}

	@Override
	public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto user) {
		return Optional.empty();
	}

	@Override
	public Optional<ProductResponseDto> deleteProduct(Long id) {
		return Optional.empty();
	}

	@Override
	public Optional<UserResponseDto> patchProduct(Long id, UserRequestDto userRequestDto) {
		return Optional.empty();
	}
}
