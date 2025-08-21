package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 * ProductService.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProductService implements IProductService {


	private final ProductRepository productRepository;


	@Override
	public Optional<ProductResponseDto> getProductById(Long id) {

		log.debug("Attempting to retrieve Product by id: {}",id);

		return Optional.empty();
	}



	@Override
	public List<ProductResponseDto> getAllProducts() {
		log.debug("Getting all Products");
		return List.of();
	}

	@Override
	public Optional<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
		log.debug("Attempting to create Product: {}", productRequestDto);

		return Optional.empty();
	}

	@Override
	public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto user) {
		log.debug("Attempting to update Product with id {}", id);
		return Optional.empty();
	}

	@Override
	public Optional<ProductResponseDto> deleteProduct(Long id) {
		log.debug("Attempting to delete Product with id: {}", id);
		return Optional.empty();
	}

	@Override
	public Optional<UserResponseDto> patchProduct(Long id, UserRequestDto userRequestDto) {
		log.debug("Attempting to patch Product with id {}",id);
		return Optional.empty();
	}
}
