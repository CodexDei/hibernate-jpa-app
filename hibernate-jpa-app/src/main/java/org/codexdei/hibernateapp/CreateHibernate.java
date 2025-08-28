package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class CreateHibernate {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{

            String name = JOptionPane.showInputDialog("Input name:");
            String lastName = JOptionPane.showInputDialog("Input lastName:");
            String payment = JOptionPane.showInputDialog("Input payment:");

            em.getTransaction().begin();
            Customer customer = new Customer();
            customer.setName(name);
            customer.setLastName(lastName);
            customer.setPaymentMethod(payment);
            em.persist(customer);
            em.getTransaction().commit();

            System.out.println("The registered customer ID is: " + customer.getId());
            customer = em.find(Customer.class, customer.getId());
            System.out.println(customer);

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }


    }
}
