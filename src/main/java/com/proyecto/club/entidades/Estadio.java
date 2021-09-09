package com.proyecto.club.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Estadio {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @ManyToOne
    private Ubicacion ubicación;

    public Ubicacion getUbicacióN() {
        return this.ubicación;
    }

    public void setUbicacióN(Ubicacion ubicación) {
        this.ubicación = ubicación;
    }
    
    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
