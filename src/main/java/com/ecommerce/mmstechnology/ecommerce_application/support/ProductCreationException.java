package com.ecommerce.mmstechnology.ecommerce_application.support;

/*
 * ProductCreationException.java
 * Project: ecommerce-application, Created by M on 20/8/2025.
 * © 2025 mmstechnology
 */
public class ProductCreationException extends RuntimeException{

	public ProductCreationException(String message){
		super(message);
	}

	public ProductCreationException(String message, Throwable cause){
		super(message,cause);
	}

}
