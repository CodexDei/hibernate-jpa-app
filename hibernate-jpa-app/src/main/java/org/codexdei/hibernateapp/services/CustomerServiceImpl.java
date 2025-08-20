package org.codexdei.hibernateapp.services;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.repositories.CrudRepository;
import org.codexdei.hibernateapp.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private EntityManager em;
    private CrudRepository<Customer> repository;

    public CustomerServiceImpl(EntityManager em) {
        this.em = em;
        this.repository = new CustomerRepository(em);
    }

    @Override
    public List<Customer> list() {
        return repository.list();
    }

    @Override
    public Optional<Customer> findId(Long id) {

        return Optional.ofNullable(repository.findId(id));
    }

    @Override
    public void save(Customer customer) {

        try {

            em.getTransaction().begin();
            repository.save(customer);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {

        try {

            em.getTransaction().begin();
            repository.delete(id);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
