package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
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

    private ProductDto input;

    @Test
    public void shouldThrowProductValidationException() {
        input = productDto(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be not null.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = productDto("valid name");

        victim.validate(input);

        verify(victim).checkNotNull(input);
    }

    private ProductDto productDto(String name) {
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        return productDto;
    }

}