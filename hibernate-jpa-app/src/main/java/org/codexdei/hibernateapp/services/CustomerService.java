package org.codexdei.hibernateapp.services;

import org.codexdei.hibernateapp.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> list();
    Optional<Customer> findId(Long id);
    void save(Customer customer);
    void delete(Long id);
}
