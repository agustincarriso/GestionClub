package com.proyecto.club.entidades;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Entrada implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2" )
     private String id;
     //private Ubicacion ubicacion;
     private Double precio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
     
     
}
