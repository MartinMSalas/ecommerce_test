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

	private Optional<Product> saveProductToRepository(Product product) {
        log.debug("Saving product to database");
        try {
            Product savedProduct = productRepository.save(product);
            log.debug("Product saved in the repository: {}", savedProduct);
            return Optional.of(savedProduct);
        } catch (Exception e) {
            log.error("Failed to save product: {}", e.getMessage(), e);
            return Optional.empty();
        }
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

		return productMapper.toDtoList(productRepository.findAll());
	}

	@Override
	public List<ProductResponseDto> getAllActiveProducts() {
		log.debug("Attempting to retrieve all active products");
		return productMapper.toDtoList(productRepository.findByActiveTrue());

	}

	@Override
	public Optional<ProductResponseDto> createProduct(ProductRequestDto productRequestDto) {
		log.debug("Attempting to create Product: {}", productRequestDto);
		return saveProductToRepository(productMapper.toProduct(productRequestDto))
				.map(product -> {
					log.debug("Product saved: {}", product);
					return productMapper.toDto(product);
				});

	}

	@Override
	public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto productRequestDto) {
		log.debug("Attempting to update Product with id {}", id);
		if(getProductByIdFromRepository(id).isEmpty()){
			return Optional.empty();
		}
		productRequestDto.setProductId(id);
		return saveProductToRepository(productMapper.toProduct(productRequestDto))
				.map(product -> {
					log.debug("Product updated {}",product);
					return productMapper.toDto(product);
				});
	}

	@Override
	public Optional<ProductResponseDto> deleteProduct(Long id) {
		log.debug("Attempting to delete Product with id: {}", id);
		return getProductByIdFromRepository(id)
				.map(product -> {
					log.debug("Product Deleted: {}",product);
					return productMapper.toDto(product);
				});
	}

	@Override
	public Optional<ProductResponseDto> patchProduct(Long id, ProductRequestDto productRequestDto) {
		log.debug("Attempting to patch Product with id {}",id);


		return getProductByIdFromRepository(id)
					.map(product -> {
						boolean changed = false;
						if (hasText(productRequestDto.getName()) && !productRequestDto.getName().equals(product.getName())) {
							product.setName(productRequestDto.getName().trim());
							changed = true;
						}
						if (hasText(productRequestDto.getDescription()) && !productRequestDto.getDescription().equals(product.getDescription())) {
							product.setDescription(productRequestDto.getDescription().trim());
							changed = true;
						}
						if (productRequestDto.getPrice() != null && (product.getPrice() == null || productRequestDto.getPrice().compareTo(product.getPrice()) != 0)) {
							product.setPrice(productRequestDto.getPrice());
							changed = true;
						}
						if (productRequestDto.getStockQuantity() != null && !productRequestDto.getStockQuantity().equals(product.getStockQuantity())) {
							product.setStockQuantity(productRequestDto.getStockQuantity());
							changed = true;
						}
						if (hasText(productRequestDto.getCategory()) && !productRequestDto.getCategory().equals(product.getCategory())) {
							product.setCategory(productRequestDto.getCategory().trim());
							changed = true;
						}
						if (hasText(productRequestDto.getImageUrl()) && !productRequestDto.getImageUrl().equals(product.getImageUrl())) {
							product.setImageUrl(productRequestDto.getImageUrl().trim());
							changed = true;
						}

						// <- Boolean PATCH semantics: only set when provided (not null)
						if (productRequestDto.getActive() != null && !productRequestDto.getActive().equals(product.getActive())) {
							product.setActive(productRequestDto.getActive());
							changed = true;
						}

						// No-op PATCH is allowed; persist only when something changed
						if (changed) {
							product = productRepository.save(product);
						}

						return productMapper.toDto(product);

					});
	}

	@Override
	public List<ProductResponseDto> searchProducts(String keyword) {
		log.debug("Request to search products with keyword: {}",keyword);

		return productMapper.toDtoList(productRepository.searchProducts(keyword));
	}

	private static boolean hasText(String s) {
		return s != null && !s.trim().isEmpty();
	}
}
