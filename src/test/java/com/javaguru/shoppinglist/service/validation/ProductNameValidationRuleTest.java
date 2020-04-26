package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {

    @Spy
    private ProductNameValidationRule victim;

    private Product input;

    @Test
    public void shouldThrowTaskValidationException() {
        input = task(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Task name must be not null.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = task("valid name");

        victim.validate(input);

        verify(victim).checkNotNull(input);
    }

    private Product task(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

}