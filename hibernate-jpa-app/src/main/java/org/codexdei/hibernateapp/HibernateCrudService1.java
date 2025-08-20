package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.services.CustomerService;
import org.codexdei.hibernateapp.services.CustomerServiceImpl;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService1 {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        CustomerService service = new CustomerServiceImpl(em);

        System.out.println("=============== List ===================");
        List<Customer> customers = service.list();
        customers.forEach(System.out::println);

        System.out.println("=============== Find by ID ===================");
        Optional<Customer> optionalCustomer = service.findId(10L);
        optionalCustomer.ifPresent(System.out::println);

        System.out.println("=============== Into new Customer ===================");
        Customer customer = new Customer();
        customer.setName("Pedro");
        customer.setLastName("Picapiedra");
        customer.setPaymentMethod("debito");
        service.save(customer);
        service.list().forEach(System.out::println);

        System.out.println("=============== Editing Customer ===================");
        Long id = customer.getId();
        Optional<Customer> optCustomer = service.findId(id);
        optCustomer.ifPresent(c -> {
            c.setPaymentMethod("Nequi");
            service.save(c);
            System.out.println("Customer successfully updated");
            service.list().forEach(System.out::println);
        });

        System.out.println("=============== Delete ===================");
        id = customer.getId();
        Optional<Customer> optCustomer2 = service.findId(id);
        optCustomer2.ifPresent(c -> {
            service.delete(c.getId());
            System.out.println("Customer successfully deleted");
            service.list().forEach(System.out::println);
        });

        em.close();

    }
}
