package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(ProductDto productDto) {
        checkNotNull(productDto);
        if (productDto.getName() == null) {
            throw new ProductValidationException("Product name must be not null.");
        }
    }
}
