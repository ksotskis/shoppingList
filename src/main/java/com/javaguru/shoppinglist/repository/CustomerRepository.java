package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class CustomerRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Long save(Customer customer) {
        sessionFactory.getCurrentSession().save(customer);
        return customer.getId();
    }

    public void update(Customer customer) {
        sessionFactory.getCurrentSession().update(customer);
    }

    public Optional<Customer> findCustomerById(Long id) {
        Customer customer = (Customer) sessionFactory.getCurrentSession().createCriteria(Customer.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        return Optional.ofNullable(customer);
    }
}
