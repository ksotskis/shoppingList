package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {

    private final Set<ProductValidationRule> validationRules;

    public ProductValidationService(Set<ProductValidationRule> validationRules) {
        this.validationRules = validationRules;
    }


    // validation just fail and throw exception??? but how you notify user about validation error?
    public void validate(Product product) {
        validationRules.forEach(s -> s.validate(product));
    }
}
