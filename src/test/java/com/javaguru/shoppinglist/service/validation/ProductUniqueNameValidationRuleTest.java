package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
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
    private ProductInMemoryRepository taskInMemoryRepository;

    @Spy
    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    private Product product = task();

    @Test
    public void shouldThrowException() {
        when(taskInMemoryRepository.existsByName(product.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Task name must be unique.");

        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        when(taskInMemoryRepository.existsByName(product.getName()))
                .thenReturn(false);

        victim.validate(product);

        verify(victim).checkNotNull(product);
    }

    private Product task() {
        Product product = new Product();
        product.setId(123L);
        product.setDescription("TEST_DESCRIPTION");
        product.setName("TEST_NAME");
        return product;
    }
}