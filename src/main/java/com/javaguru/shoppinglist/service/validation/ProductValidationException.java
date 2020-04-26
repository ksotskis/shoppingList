package com.javaguru.shoppinglist.service.validation;

class ProductValidationException extends RuntimeException {

    ProductValidationException(String message) {
        super(message);
    }
}
