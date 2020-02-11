package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public class ProductService {

    private ProductInMemoryRepository repository = new ProductInMemoryRepository();
    private ProductValidationService validationService = new ProductValidationService();


    // validate and insert ??? But what happens whn validation fail??
    // do you return error message to user with error explanation?
    public Product createProduct(Product product) {
        validationService.validate(product);
        repository.insert(product);
        return product;
    }

    public Product findProductByID(Long id) {
        return repository.findProductById(id);
    }
}
