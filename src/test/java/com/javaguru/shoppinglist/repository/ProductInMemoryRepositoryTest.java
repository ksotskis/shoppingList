package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ProductInMemoryRepositoryTest {

    private static final String TASK_NAME = "TEST_NAME";
    private static final String TASK_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long TASK_ID = 0L;

    private ProductInMemoryRepository victim = new ProductInMemoryRepository();

    private Product product = task();

    @Test
    public void shouldInsert() {
        Product result = victim.save(product);

        assertThat(result).isEqualTo(expectedTask());
    }

    @Test
    public void shouldFindById() {
        victim.save(product);

        Optional<Product> result = victim.findProductById(TASK_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedTask());
    }

    @Test
    public void shouldFindByName() {
        victim.save(product);

        Optional<Product> result = victim.findProductByName(TASK_NAME);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedTask());
    }

    @Test
    public void shouldExistByName() {
        victim.save(product);

        boolean result = victim.existsByName(TASK_NAME);
        assertThat(result).isTrue();
    }

    private Product expectedTask() {
        Product product = new Product();
        product.setName(TASK_NAME);
        product.setDescription(TASK_DESCRIPTION);
        product.setId(TASK_ID);
        return product;
    }

    private Product task() {
        Product product = new Product();
        product.setName(TASK_NAME);
        product.setDescription(TASK_DESCRIPTION);
        return product;
    }
}