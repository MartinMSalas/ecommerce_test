package com.ecommerce.mmstechnology.ecommerce_application.mapper;

import com.ecommerce.mmstechnology.ecommerce_application.dto.request.ProductRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.ProductResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

/*
 * ProductMapper.java
 * Project: ecommerce-application, Created by M on 18/8/2025.
 * © 2025 mmstechnology
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {


	ProductResponseDto toDto(Product product);

	Product toProduct(ProductRequestDto productRequestDto);
	List<ProductResponseDto> toDtoList(List<Product> products);
}
