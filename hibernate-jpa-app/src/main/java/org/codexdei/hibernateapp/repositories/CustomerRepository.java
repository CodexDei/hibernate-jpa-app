package org.codexdei.hibernateapp.repositories;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;

import java.util.List;

public class CustomerRepository implements CrudRepository<Customer> {

    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Customer> list() {
        return em.createQuery("select c from Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer findId(Long id) {

        return em.find(Customer.class,id);
    }

    @Override
    public void save(Customer customer) {

        if (customer.getId() != null && customer.getId() > 0){

            em.merge(customer);
        }else {
            em.persist(customer);
        }
    }

    @Override
    public void delete(Long id) {

        Customer customer = findId(id);
        em.remove(customer);
    }
}
