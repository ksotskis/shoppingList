package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Customer;
import com.javaguru.shoppinglist.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found, id: " + customerId));
    }

}
