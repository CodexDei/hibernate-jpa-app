package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class SingleResultWhereHibernate {

    private static boolean statusProgram = true;

    public static void main(String[] args) {

        do {

        Scanner sc = new Scanner(System.in);

            System.out.println("1.Program execute");
            System.out.println("2. Exit");
            System.out.println("Into option:");
            int opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {

                    EntityManager em = JpaUtil.getEntityManager();
                    Query query = em.createQuery("select c from Customer c where c.paymentMethod=?1", Customer.class);
                    System.out.println("Into a payment Method:");
                    String payment = sc.next();
                    query.setParameter(1, payment);
                    query.setMaxResults(1);
                    //query.getSingleResult(); solo se puede usar si la base de datos solo arroja un resultado
                    //Si llega a tener mas resultados lanzara una exception: NonUniqueResultException
                    try {
                        Customer customer = (Customer) query.getSingleResult();
                        System.out.println(customer);
                    }catch (NonUniqueResultException e){
                        e.printStackTrace();
                        System.out.println("The program is limited to showing one record only");
                        System.out.println("Exception: NonUniqueResultException");
                    }finally {
                    //cerramos la conexion, siempre hay que cerrarla
                    em.close();
                    }
                }
                case 2 -> {

                    statusProgram = false;
                }
            }
        } while (statusProgram);
    }
}
