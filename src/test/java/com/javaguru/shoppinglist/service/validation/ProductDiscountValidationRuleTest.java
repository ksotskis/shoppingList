package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductDiscountValidationRuleTest {

    @Test
    public void test1() {
        ProductDiscountValidationRule productDiscountValidationRule = new ProductDiscountValidationRule();
        Product product = new Product();
        product.setDiscount(110);
        try {
            productDiscountValidationRule.validate(product);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Discount can't be more than 100");
        }
    }
}