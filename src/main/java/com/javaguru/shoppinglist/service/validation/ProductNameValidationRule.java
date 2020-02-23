package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationRule;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        if (product.getName() != null) {
            if ((product.getName().length() < 3) || (product.getName().length() > 32)) {
                throw new ProductValidationException("Name can't be less than 2 symbols or more than 32");
            }
        }
    }
}
