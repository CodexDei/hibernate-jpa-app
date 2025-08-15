package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateList {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        List<Customer> customers = em.createQuery("select c from Customer c", Customer.class).getResultList();
        //listar con un forEach
        customers.forEach(System.out::println);
        //cerramos la conexion
        em.close();
    }
}
