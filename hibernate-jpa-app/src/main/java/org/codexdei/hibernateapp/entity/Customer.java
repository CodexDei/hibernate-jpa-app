package org.codexdei.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String paymentMethod;
    //COn la siguiente anotacion puede utilizar los atributos de otra clase que tenga la anotacion "@Embeddable"
    @Embedded
    private Audit audit = new Audit();

    //siempre que se usa entity y se tenga un constructor con parametros se
    // tiene que implementar un constructor vacio, sino al crear un objeto tipo Cliente lanara un error
    public Customer() {
    }

    public Customer(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }

    public Customer(Long id, String name, String lastName, String paymentMethod) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {

        LocalDateTime created = this.audit != null ? audit.getCreatedIn() : null;
        LocalDateTime edited = this.audit != null ? audit.getEditedIn() : null;

        return  "idCustomer=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", payment_method=" + paymentMethod +
                ", cretedIn='" + created + '\'' +
                ", editedIn='" + edited + '\'';
    }
}
