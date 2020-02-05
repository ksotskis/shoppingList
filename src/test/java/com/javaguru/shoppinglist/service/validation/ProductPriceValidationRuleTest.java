package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductPriceValidationRuleTest {
    @Test
    public void test1() {
        ProductPriceValidationRule productPriceValidationRule = new ProductPriceValidationRule();
        Product product = new Product();
        product.setPrice(new BigDecimal(-1));
        try {
            productPriceValidationRule.validate(product);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Price can't be less than 0");
        }
    }

}