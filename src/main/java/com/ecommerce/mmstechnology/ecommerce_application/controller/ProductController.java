package com.ecommerce.mmstechnology.ecommerce_application.controller;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ResourceNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.service.IProductService;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ProductCreationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * ProductController.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
@Slf4j
public class ProductController {

	final IProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
		log.info("Request for getting all products");

		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/active")
	public ResponseEntity<List<ProductResponseDto>> getAllActiveProducts(){
		log.info("Request for getting all active products");
		return new ResponseEntity<>(productService.getAllActiveProducts(), HttpStatus.OK);
	}


	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable(name="id")  @Min(1) Long id){
		log.info("Request for get product with id: {}",id);


		return productService.getProductById(id).map(productDto ->{
				log.debug("Product Dto Response found {}",productDto);
				return new ResponseEntity<>(productDto,HttpStatus.OK);
			}).orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
	}

	@GetMapping("/search")
	public ResponseEntity<List<ProductResponseDto>> searchProducts(@RequestParam("name") String keyword){
		log.info("Request to search products with keyword: {}",keyword);
		return new ResponseEntity<>(productService.searchProducts(keyword),HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<ProductResponseDto> createProduct(@RequestBody @Valid ProductRequestDto productRequestDto){
		log.info("Request for create product: {}", productRequestDto);
//		throw new ProductCreationException("Unable to create product");
		return productService.createProduct(productRequestDto)
				.map(productResponseDto -> {
					log.debug("Product created with id: {}", productResponseDto.getProductId());
					return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);
				}).orElseThrow(()-> {
							log.warn("Product creation failed for request: {}", productRequestDto);
							return new ProductCreationException("Unable to create the product.");
						}
				);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ProductResponseDto> deleteProductById(@PathVariable(name = "productId") @Min(1) Long productId){
		log.info("Request to delete Product with id: {}",productId);

		return productService.deleteProduct(productId)
				.map(productResponseDto->{
					log.debug("Product deleted sending response entity");
					return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
				})
				.orElseThrow(()->{
					return new ResourceNotFoundException("Product not found with ID: {}" + productId);
				}
				);

	}

	@PatchMapping("/{productId}")
	public ResponseEntity<ProductResponseDto> patchProductById(@PathVariable(name="productId") @Min(1) Long productId, @RequestBody ProductRequestDto productRequestDto){
		log.info("Request to patch Product with id: {}", productId);

		return productService.patchProduct(productId, productRequestDto)
				.map(productResponseDto -> {
					log.debug("Product patched {}",productResponseDto);
					return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
				})
				.orElseThrow(() -> {

					return new ResourceNotFoundException("Product not found with ID: " + productId);
				});
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ProductResponseDto> updateProductById(@PathVariable(name="productId") @Min(1) Long productId,@RequestBody ProductRequestDto productRequestDto){
		log.info("Request to update Product with id: {}", productId);

		return productService.updateProduct(productId, productRequestDto)
				.map(productResponseDto -> {
					log.debug("Product updated: {}", productResponseDto);
					return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
				})
				.orElseThrow(()-> {
					return new ResourceNotFoundException("Product not found with ID: " + productId);
				});
	}



}
