package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.mapper.ProductConverter;
import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @Mock
    private ProductConverter productConverter;

    @Captor
    private ArgumentCaptor<ProductDto> productCaptor;

    private ProductService victim;

    @Before
    public void setUp() throws Exception {
        victim = new ProductService(repository, validationService, productConverter);
    }

    @Test
    public void shouldCreateProduct() {
        ProductDto productDto = productDto();
        Product product = product();
        when(productConverter.convert(productDto)).thenReturn(product);
        when(repository.save(product)).thenReturn(product.getId());

        Long result = victim.createProduct(productDto);

        verify(validationService).validate(productCaptor.capture());
        ProductDto captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(productDto);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(1001L)).thenReturn(Optional.ofNullable(product()));
        when(productConverter.convert(product())).thenReturn(productDto());

        ProductDto result = victim.findProductById(1001L);

        assertThat(result).isEqualTo(productDto());
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(1001L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product not found, id: 1001");
    }

    private ProductDto productDto() {
        ProductDto product = new ProductDto();
        product.setName("TEST_NAME");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(1001L);
        return product;
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(1001L);
        return product;
    }
}