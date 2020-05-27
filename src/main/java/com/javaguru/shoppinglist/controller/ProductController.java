package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.dto.ProductDto;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/tasks")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService taskService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated({ProductDto.Create.class}) @RequestBody ProductDto productDto,
                                       UriComponentsBuilder builder) {
        Long id = productService.createProduct(productDto);
        return ResponseEntity.created(builder.path("/products/{id}").buildAndExpand(id).toUri()).build();
    }


    @PutMapping("/{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @PathVariable("customerId") Long customerId, @Validated({ProductDto.Update.class}) @RequestBody ProductDto productDto) {
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping(params = "name")
    public ProductDto findProductByName(@RequestParam("name") String name) {
        return productService.findProductByName(name);
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException ex) {

    }

}
