package com.ecommerce.mmstechnology.ecommerce_application.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductWithoutStockNeeded extends RuntimeException {
    public ProductWithoutStockNeeded(String message) {
        super(message);
        log.debug(message);
    }
}
