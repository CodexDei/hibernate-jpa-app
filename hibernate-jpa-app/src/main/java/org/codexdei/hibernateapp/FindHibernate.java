package org.codexdei.hibernateapp;

import jakarta.persistence.EntityManager;
import org.codexdei.hibernateapp.entity.Customer;
import org.codexdei.hibernateapp.util.JpaUtil;

import java.util.Scanner;

//Esta clase permite buscar un cliente mediante su ID
public class FindHibernate {

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

                    System.out.println("Enter the ID to search:");
                    Long id = sc.nextLong();
                    EntityManager em = JpaUtil.getEntityManager();
                    //find solo permite buscar por medio de la llave primaria
                    Customer customer = em.find(Customer.class,id);
                    System.out.println(customer);
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
