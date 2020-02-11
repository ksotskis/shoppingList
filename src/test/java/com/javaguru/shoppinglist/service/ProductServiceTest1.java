package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest1 {
    @Mock
    private ProductInMemoryRepository repository;
    @Mock
    private ProductValidationService validationService;
    @InjectMocks
    private ProductService victim;
    @Captor
    private ArgumentCaptor<Product> taskCaptor;

    @Test
    public void shouldCreateTaskSuccessfully() {
        Product product = product();
        when(repository.insert(product)).thenReturn(product);
        Product result = victim.createProduct(product);
        verify(validationService).validate(taskCaptor.capture());
        Product captorResult = taskCaptor.getValue();
        assertEquals(captorResult, product);
        assertEquals(product(), result);

    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(1001L);
        return product;
    }
}