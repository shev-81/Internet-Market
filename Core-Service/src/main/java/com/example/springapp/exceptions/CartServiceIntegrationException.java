package com.example.springapp.exceptions;

public class CartServiceIntegrationException extends RuntimeException {
    public CartServiceIntegrationException(String message) {
        super(message);
    }
}
