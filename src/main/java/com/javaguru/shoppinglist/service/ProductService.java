package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public class ProductService {

    private ProductInMemoryRepository repository;
    private ProductValidationService validationService;

    public ProductService(ProductInMemoryRepository repository,
                          ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Product createProduct(Product product) {
        validationService.validate(product);
        repository.insert(product);
        return product;
    }

    public Product findProductByID(Long id) {
        return repository.findProductById(id);
    }
}
