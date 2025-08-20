package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class UpdateHibernate {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            //Usuario ingresa id a buscar en la DB
            Long id = Long.valueOf(JOptionPane.showInputDialog("Input Id to find:"));
            Customer customer = em.find(Customer.class,id);

            String name = JOptionPane.showInputDialog("Input name", customer.getName());
            String lastName = JOptionPane.showInputDialog("Input lastName", customer.getLastName());
            String payment = JOptionPane.showInputDialog("Input payment method", customer.getPaymentMethod());
            //buscamos ese id y guardamos los datos del cliente
            //Iniciamos la transaccion
            em.getTransaction().begin();
            //SOlicitamos al usuario los datos a modificar
            customer.setName(name);
            customer.setLastName(lastName);
            customer.setPaymentMethod(payment);
            em.merge(customer);
            em.getTransaction().commit();
            //mostramos en consola
            System.out.println(customer);

        }catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            em.close();
        }
    }
}
