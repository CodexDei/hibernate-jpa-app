package org.codexdei.hibernateapp;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class DeleteHibernate {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Input the customer ID to delete:");
        Long id = sc.nextLong();

        EntityManager em = JpaUtil.getEntityManager();

        try{

        Customer customer = em.find(Customer.class,id);
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();

        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
