package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUI {

    private ProductService productService = new ProductService();

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        System.out.println("Enter product category: ");
                        String category = scanner.nextLine();
                        System.out.println("Enter product discount: ");
                        int discount = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter product description: ");
                        String description = scanner.nextLine();
                        Product product = new Product();
                        product.setName(name);
                        product.setPrice(price);
                        product.setCategory(category);
                        product.setDiscount(discount);
                        product.setDescription(description);
                        productService.createProduct(product);
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        productService.findProductByID(id);
                        Product findProductResult = productService.findProductByID(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
}

