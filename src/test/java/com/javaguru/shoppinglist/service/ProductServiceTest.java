package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductInMemoryRepository repository;

    @Mock
    private ProductValidationService validationService;

    @Captor
    private ArgumentCaptor<Product> taskCaptor;

    private ProductService victim;

    @Before
    public void setUp() throws Exception {
        victim = new ProductService(repository, validationService);
    }

    @Test
    public void shouldCreateTask() {
        Product product = task();
        when(repository.save(product)).thenReturn(product);

        Long result = victim.createProduct(product);

        verify(validationService).validate(taskCaptor.capture());
        Product captorResult = taskCaptor.getValue();

        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindTaskById() {
        when(repository.findProductById(1001L)).thenReturn(Optional.ofNullable(task()));

        Product result = victim.findProductById(1001L);

        assertThat(result).isEqualTo(task());
    }

    @Test
    public void shouldThrowExceptionTaskNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(1001L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task not found, id: 1001");
    }

    private Product task() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(1001L);
        return product;
    }
}