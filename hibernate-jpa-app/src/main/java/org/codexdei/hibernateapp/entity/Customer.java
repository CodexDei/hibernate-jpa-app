package org.codexdei.hibernateapp.entity;

import jakarta.persistence.*;
//relaciona esta clase con la table en la DB o BD
@Entity
//Como el nombre de la clase es diferente al de la DB se
//Coloca esta anotacion y se indica el nombre que tiene en la base de datos
@Table(name = "clientes")
public class Customer {

    //las siguientes dos anotaciones conectan el id que es tambien una llave primaria con
    //esta clase
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //como los nombres de la columnas de la DB son diferentes se coloca la
    //siguiente anotacion con el nombre que tiene en la tabla
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellido")
    private String lastName;
    @Column(name = "forma_pago")
    private String payment_method;

    //siempre que se usa entity y se tenga un constructor con parametros se
    // tiene que implementar un constructor vacio, sino al crear un objeto tipo Cliente lanara un error
    public Customer() {
    }

    public Customer(Long id, String name, String lastName, String payment_method) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.payment_method = payment_method;
    }

    @Override
    public String toString() {
        return  "idCustumer=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", payment_method=" + payment_method +
                '}';
    }
}
