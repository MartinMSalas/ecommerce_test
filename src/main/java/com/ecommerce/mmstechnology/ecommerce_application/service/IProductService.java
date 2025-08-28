package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;

import java.util.List;
import java.util.Optional;

/*
 * IProductService.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
public interface IProductService {

	public Optional<ProductResponseDto> getProductById(Long id);

	public List<ProductResponseDto> getAllProducts();

	public Optional<ProductResponseDto> createProduct(ProductRequestDto user);

	public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto user);

	public Optional<ProductResponseDto> deleteProduct(Long id);

	public Optional<ProductResponseDto> patchProduct(Long id, ProductRequestDto productRequestDto);

}