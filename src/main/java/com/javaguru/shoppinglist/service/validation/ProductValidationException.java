package com.javaguru.shoppinglist.service.validation;

public class ProductValidationException extends RuntimeException {

    ProductValidationException(String message) {
        super(message);
    }
}
