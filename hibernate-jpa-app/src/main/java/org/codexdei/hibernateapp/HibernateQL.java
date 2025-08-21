package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Objects;

public class HibernateQL {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("============== Query All Records ============");
        List<Customer> customers = em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);

        System.out.println("============== Query by ID ============");
        //se puede omitir el select c para hacerlo mas corto, funciona igual
        //":idCustomer" es otra forma de darle un alias al parametro, por lo segular se usa '?'
        Customer customer = em.createQuery("from Customer c where c.id=:idCustomer" , Customer.class)
                //"idCustomer" debe ser igual al alias de la sentencia anterior, el segundo parametro(en este
                // caso 1L, es el ID a buscar)
                .setParameter("idCustomer",1L)
                .getSingleResult();
        System.out.println(customer);

        System.out.println("============== Query only the name by ID ============");
        String nameCustomer = em.createQuery("select c.name from Customer c where id=:idCustomer", String.class)
                .setParameter("idCustomer",2L)
                .getSingleResult();
        System.out.println(nameCustomer);

        System.out.println("============== Queries by custom fields ================");
        Object[] objects = em.createQuery("select c.id, c.name, c.lastName from Customer c where c.id=:idCustomer",Object[].class)
                .setParameter("idCustomer", 3L)
                .getSingleResult();
        Long id = (Long) objects[0];
        String name = (String) objects[1];
        String lastName = (String) objects[2];
        System.out.println("ID:" + id + " Name:" + name + " Lastname:" + lastName);

        System.out.println("============== Queries by custom fields ================");
        List<Object[]> registries = em.createQuery("select c.id, c.name, c.lastName from Customer c",Object[].class)
                .getResultList();
        //for (Object[] reg : registries){
        registries.forEach( reg -> {
            Long idCus = (Long) reg[0];
            String nameCus = (String) reg[1];
            String lastNameCus = (String) reg[2];
            System.out.println("ID:" + idCus + " Name:" + nameCus + " Lastname:" + lastNameCus

































































            );
        });

    }
}
