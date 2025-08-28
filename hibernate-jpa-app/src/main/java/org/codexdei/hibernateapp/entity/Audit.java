package org.codexdei.hibernateapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

//Sirve para que otra clase pueda utilizar los atributos de esta clase
@Embeddable
public class Audit {

    @Column(name = "creado_en")
    private LocalDateTime createdIn;
    @Column(name = "editado_en")
    private LocalDateTime editedIn;

    @PrePersist
    public void prePersist(){

        System.out.println("Initializing something before persisting or inserting");
        this.createdIn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){

        System.out.println("Initializing something before Updating");
        this.editedIn = LocalDateTime.now();
    }


    public LocalDateTime getCreatedIn(){
        return this.createdIn;
    }

    public void setCreatedIn(LocalDateTime createdIn){
        this.createdIn = createdIn;
    }

    public LocalDateTime getEditedIn() {
        return this.editedIn;
    }

    public void setEditedIn(LocalDateTime editedIn) {
        this.editedIn = editedIn;
    }

}
