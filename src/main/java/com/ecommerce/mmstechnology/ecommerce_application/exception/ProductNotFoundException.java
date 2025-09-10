package com.ecommerce.mmstechnology.ecommerce_application.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
        log.warn(message);


    }
}
