package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductNameValidationRuleTest {

    @Test
    public void test1() {
        ProductNameValidationRule productNameValidationRule = new ProductNameValidationRule();
        Product product = new Product();
        product.setName("Go");
        try {
            productNameValidationRule.validate(product);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Name can't be less than 2 symbols or more than 32");
        }
    }
}