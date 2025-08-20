package com.ecommerce.mmstechnology.ecommerce_application.support;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;

/*
 * GlobalExceptionHandler.java
 * Project: ecommerce-application, Created by M on 20/8/2025.
 * © 2025 mmstechnology
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductCreationException.class)
    public ProblemDetail handleProductCreation(ProductCreationException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
        pd.setTitle("Product creation failed");
        pd.setType(URI.create("https://example.com/problems/product-creation-failed"));
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        pd.setTitle("Invalid request");
        pd.setType(URI.create("https://example.com/problems/validation-error"));
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        pd.setProperty("errors", ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage()).toList());
        return pd;
    }


}
