package com.ecommerce.mmstechnology.ecommerce_application.service;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.mapper.ProductMapper;
import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
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
	private final ProductMapper productMapper;


	private Optional<Product> getProductByIdFromRepository(Long productId){
		log.debug("Attempting to find product in the repository with id: {}", productId);

		return productRepository.findById(productId)
				.map(product -> {
					log.debug("Product found: {}",product);
					return product;
				});

	}

	private Optional<Product> saveProductToRepository(Product product){
		log.debug("Saving product to database");

		return Optional.of(productRepository.save(product))
				.map(productSaved -> {
					log.debug("Product saved: {}",productSaved);
					return productSaved;
				});


	}
	@Override
	public Optional<ProductResponseDto> getProductById(Long id) {

		log.debug("Attempting to retrieve Product by id: {}",id);

		return getProductByIdFromRepository(id)
				.map(productMapper::toDto);

	}



	@Override
	public List<ProductResponseDto> getAllProducts() {
		log.debug("Getting all Products");

		return productRepository.findAll();
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
