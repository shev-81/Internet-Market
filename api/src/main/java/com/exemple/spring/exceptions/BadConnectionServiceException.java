package com.exemple.spring.exceptions;

public class BadConnectionServiceException extends AppError{

    public enum CartServiceErrors {
        SERVICE_SHUTDOWN, CART_NOT_FOUND
    }

    private CartServiceErrors code;

    public BadConnectionServiceException() {
        super();
    }

    public BadConnectionServiceException(Integer statusCode, String message) {
        super(statusCode, message);
    }


    public BadConnectionServiceException(Integer statusCode, String message, CartServiceErrors code) {
        super(statusCode, message);
        this.code = code;
    }



    public CartServiceErrors getCode() {
        return code;
    }

    public void setCode(CartServiceErrors cartServiceErrors) {
        this.code = cartServiceErrors;
    }
}
