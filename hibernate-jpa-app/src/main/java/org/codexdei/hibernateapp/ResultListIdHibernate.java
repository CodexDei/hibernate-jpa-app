package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class ResultListIdHibernate {

    private static boolean statusProgram = true;

    public static void main(String[] args) {

        do {

        Scanner sc = new Scanner(System.in);

            System.out.println("**********************************************");
            System.out.println("1.Program execute");
            System.out.println("2. Exit");
            System.out.println("Into option:");
            int opcion = sc.nextInt();
            System.out.println("**********************************************");

            switch (opcion) {

                case 1 -> {

                    EntityManager em = JpaUtil.getEntityManager();
                    Query query = em.createQuery("select c from Customer c where c.paymentMethod=?1", Customer.class);
                    System.out.println("Enter the payment method to search:");
                    String payment = sc.next()  ;
                    query.setParameter(1, payment);
                    //query.getSingleResult(); solo se puede usar si la base de datos solo arroja un resultado
                    //Si llega a tener mas resultados lanzara una exception: NonUniqueResultException
                    List<Customer> customers = query.getResultList();
                    customers.forEach(System.out::println);
                    em.close();
                    }
                case 2 -> {
                    statusProgram = false;
                }
                default -> {

                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("Invalid option, into 1 or 2");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
                }
            }
        } while (statusProgram);
    }
}
