package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.services.CustomerService;
import org.codexdei.hibernateapp.services.CustomerServiceImpl;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudService2 {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        CustomerService service = new CustomerServiceImpl(em);

        System.out.println("=============== List ===================");
        List<Customer> customers = service.list();
        customers.forEach(System.out::println);

        System.out.println("=============== Find by ID ===================");
        Optional<Customer> optionalCustomer = service.findId(100L);
        optionalCustomer.ifPresent(System.out::println);

        System.out.println("=============== Save ===================");
        System.out.println("==Update=======");
        Optional<Customer> optionalCustomer2 = service.findId(10L);
        String name = "Dixon";
        String lastName = "Oyola";
        String payment = "nequi";
        optionalCustomer2.get().setName(name);
        optionalCustomer2.get().setLastName(lastName);
        optionalCustomer2.get().setPaymentMethod(payment);
        Customer customer = optionalCustomer2.orElse(null);
        service.save(customer);
        System.out.println("== Create =======");
        Customer newCustomer = new Customer();
        newCustomer.setName("William");
        newCustomer.setLastName("Romero");
        newCustomer.setPaymentMethod("Nequi");
        service.save(newCustomer);
        service.list().forEach(System.out::println);
        System.out.println("=============== Delete ===================");
        service.delete(10L);


        em.close();

    }
}
