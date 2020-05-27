package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidationRuleTest {

    @Mock
    private ProductRepository hibernateProductRepository;

    @Spy
    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    private ProductDto productDto = productDto();

    @Test
    public void shouldThrowException() {
        when(hibernateProductRepository.existsByName(productDto.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(productDto))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be unique.");

        verify(victim).checkNotNull(productDto);
    }

    @Test
    public void shouldValidateSuccess() {
        when(hibernateProductRepository.existsByName(productDto.getName()))
                .thenReturn(false);

        victim.validate(productDto);

        verify(victim).checkNotNull(productDto);
    }

    private ProductDto productDto() {
        ProductDto productDto = new ProductDto();
        productDto.setId(123L);
        productDto.setDescription("TEST_DESCRIPTION");
        productDto.setName("TEST_NAME");
        return productDto;
    }
}