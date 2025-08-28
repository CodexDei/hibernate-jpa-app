package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.domain.CustomerDTO;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;
import org.hibernate.hql.internal.ast.tree.IdentNode;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToStdout;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
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
        Customer customer = em.createQuery("from Customer c where c.id=:idCustomer", Customer.class)
                //"idCustomer" debe ser igual al alias de la sentencia anterior, el segundo parametro(en este
                // caso 1L, es el ID a buscar)
                .setParameter("idCustomer", 1L)
                .getSingleResult();
        System.out.println(customer);

        System.out.println("============== Query only the name by ID ============");
        String nameCustomer = em.createQuery("select c.name from Customer c where id=:idCustomer", String.class)
                .setParameter("idCustomer", 2L)
                .getSingleResult();
        System.out.println(nameCustomer);

        System.out.println("============== Query by custom fields ================");
        Object[] objects = em.createQuery("select c.id, c.name, c.lastName from Customer c where c.id=:idCustomer", Object[].class)
                .setParameter("idCustomer", 3L)
                .getSingleResult();
        Long id = (Long) objects[0];
        String name = (String) objects[1];
        String lastName = (String) objects[2];
        System.out.println("ID:" + id + " Name:" + name + " Lastname:" + lastName);

        System.out.println("============== Query by custom fields ================");
        List<Object[]> registries = em.createQuery("select c.id, c.name, c.lastName from Customer c", Object[].class)
                .getResultList();
        //for (Object[] reg : registries){
        registries.forEach(reg -> {
            Long idCus = (Long) reg[0];
            String nameCus = (String) reg[1];
            String lastNameCus = (String) reg[2];
            System.out.println("ID:" + idCus + " Name:" + nameCus + " Lastname:" + lastNameCus
            );
        });

        System.out.println("============== Query by customer and payment method ================");
        registries = em.createQuery("select c, c.paymentMethod from Customer c", Object[].class)
                .getResultList();
        registries.forEach(reg -> {
            Customer c = (Customer) reg[0];
            String payment = (String) reg[1];
            System.out.println("Payment Metrod=" + payment + "," + c);
        });

        System.out.println("============== Query that populates and returns an entity object of a custom class ================");
        customers = em.createQuery("select new Customer(c.name, c.lastName) from Customer c", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);


        System.out.println("============== Query that populates and returns an DTO object of a custom class ================");
        List<CustomerDTO> customerDTOS = em.createQuery("select new org.codexdei.hibernateapp.domain.CustomerDTO(c.name,c.lastName) from Customer c", CustomerDTO.class)
                .getResultList();
        customerDTOS.forEach(System.out::println);

        System.out.println("============== Query with the names of customers ================");
        List<String> names = em.createQuery("select c.name from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        System.out.println("============== Query with distinct customers names ================");
        names = em.createQuery("select distinct(c.name) from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        System.out.println("============== Query with distinct payment methods ================");
        List<String> paymentsDistinct = em.createQuery("select distinct(c.paymentMethod) from Customer c", String.class)
                .getResultList();
        paymentsDistinct.forEach(System.out::println);

        System.out.println("============== Query to count the payment method  ================");
        Long paymmentMethodNumber = em.createQuery("select count(distinct c.paymentMethod) from Customer c", Long.class)
                .getSingleResult();
        System.out.println("Number payment method: " + paymmentMethodNumber);

        //Muestra nombre y apellido concatendados
        System.out.println("================== Query with name and lastName concatenated ==============");
        names = em.createQuery("select concat(c.name, ' ', c.lastName) as namelastname from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        //Muestra nombre y apellido concatendados
        System.out.println("================== Query with name and lastName concatenated ==============");
        names = em.createQuery("select c.name || ' ' || c.lastName as namelastname from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        System.out.println("================== Query with name and lastName upper concatenated ==============");
        names = em.createQuery("select upper(concat(c.name,' ',c.lastName)) as namelastname from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        System.out.println("================== Query with name and lastName lower concatenated ==============");
        names = em.createQuery("select lower(concat(c.name,' ',c.lastName)) as namelastname from Customer c", String.class)
                .getResultList();
        names.forEach(System.out::println);

        System.out.println("================== Query to search by name ==============");
        String param = "ui";
        //busca coincideas de nombres segun el "param"(variable de la linea anterior),es decir nombres que contengan esas letras
        //mysql no es sensible a mayusculas o minusculas por lo que se podria escribir en mayusculas o minusculas
        //se coloca "upper" para convertir a mayusculas, ES UNA BUENA PRACTICA PARA CASOS DONDE SE USO OTRO MOTOR SQL que
        //no sea mysql y que si sea sensible a mayusculas o minusculas
        customers = em.createQuery("select c from Customer c where upper(c.name) like upper(:parameter)", Customer.class)
                //los porcentajes("%")se colocan para que busque coincidencias en todo el nombre, sino se colocan
                //no hace la busqueda
                .setParameter("parameter", "%" + param + "%")
                .getResultList();
        customers.forEach(System.out::println);

        System.out.println("================== Query between numbers ==============");
        //consultas entre(between) rangos numericos
        customers = em.createQuery("select c from Customer c where c.id between 5 and 10", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);

        System.out.println("================== Query between letters ==============");
        customers = em.createQuery("select c from Customer c where c.name between 'L' and 'Z'", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);

        System.out.println("================== Query Order by ==============");
        //oder por algun elemento, en el siguiente ejemplo por nombres, asc=ascendente des=descendente
        customers = em.createQuery("select c from Customer c order by c.name asc", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);
        //Tambien se puede ordenar por un elemento, luego por otro, por ejemplo por apellido y si hay
        //apellidos repetidos ordena por nombre
        customers = em.createQuery("select c from Customer c order by c.lastName asc, c.name desc", Customer.class)
                .getResultList();
        customers.forEach(System.out::println);

        System.out.println("================== Query count ==============");
        //contar elementos
        Long total = em.createQuery("select count(c) as total from Customer c", Long.class)
                .getSingleResult();
        System.out.println("Customers Total:" + total);

        System.out.println("================== Query min ==============");
        Long minId = em.createQuery("select min(c.id) as min from Customer c", Long.class).getSingleResult();
        System.out.println("Min ID: " + minId);

        System.out.println("================== Query max ==============");
        Long maxId = em.createQuery("select max(c.id) as max from Customer c", Long.class).getSingleResult();
        System.out.println("max ID:" + maxId);

        System.out.println("================== Query the name and its length ==============");
        registries = em.createQuery("select c.name, length(c.name) from Customer c", Object[].class)
                .getResultList();
        registries.forEach(reg -> {
            String nam = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("Name = " + nam + ", lenght = " + length);
        });

        System.out.println("================== Query the shortest name ==============");
        Integer nameShortest = em.createQuery("select min(length(c.name)) from Customer c", Integer.class).getSingleResult();
        System.out.println("Shortest Name: " + nameShortest);

        System.out.println("================== Query the longest name ==============");
        Integer nameLongest = em.createQuery("select max(length(c.name)) from Customer c", Integer.class).getSingleResult();
        System.out.println("Longest Name: " + nameLongest);

        System.out.println("================== Query statistics ==============");
        Object[] statistics = em.createQuery("select min(c.id), max(c.id), count(c.id), sum(c.id), avg(length(c.lastName)) from Customer c",
                        Object[].class)
                .getSingleResult();
        Long minSta = (Long) statistics[0];
        Long maxSta = (Long) statistics[1];
        Long count = (Long) statistics[2];
        Long sum = (Long) statistics[3];
        Double avg = (Double) statistics[4];
        System.out.println("Min id = " + minSta);
        System.out.println("Max id = " + maxSta);
        System.out.println("Count id = " + count);
        System.out.println("Sum id = " + sum);
        System.out.println("Average lastName = " + avg);

        System.out.println("================== Query the shortest name and its length ==============");
        registries = em.createQuery("select c.name, length(c.name) from Customer c where " +
                        "length(c.name) = (select min(length(c.name)) from Customer c)", Object[].class)
                .getResultList();
        registries.forEach(reg -> {
            String nam = (String) reg[0];
            Integer length = (Integer) reg[1];
            System.out.println("Shortest name:" + nam + ", length: " + length);
        });

        System.out.println("================== Query the largest name its length ==============");
        registries = em.createQuery("select c.name, length(c.name) from Customer c where " +
                        "length(c.name) = (select max(length(c.name)) from Customer c)", Object[].class)
                .getResultList();
        registries.forEach(reg -> {
            String na = (String) reg[0];
            Integer leng = (Integer) reg[1];
            System.out.println("Largest name:" + na + ", length:" + leng);
        });

        System.out.println("================== Query to get the last record ==============");
        Customer lastCustomer = em.createQuery("select c from Customer c where c.id = (select max(c.id) from Customer c)", Customer.class)
                .getSingleResult();
        System.out.println("last record: " + lastCustomer);

        System.out.println("================== Query where in ==============");
        //consulta que permite buscar ciertos datos dentro de una lista, array, etc
        customers = em.createQuery("select c from Customer c where c.id in :ids", Customer.class)
                .setParameter("ids", Arrays.asList(1L,2L,3L, 7L))
                .getResultList();
        customers.forEach(System.out::println);

        customers = em.createQuery("select c from Customer c where c.lastName in :lastnames",Customer.class)
                .setParameter("lastnames", Arrays.asList("Castillo", "Hortua", "Oyola"))
                .getResultList();
        customers.forEach(System.out::println);

        //Cerramos conexion
        em.close();
    }
}
