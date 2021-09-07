package com.proyecto.club.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.proyecto.club.enums.Talle;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private double precio;
    private Talle talle;
    private String nombre;
    private String descripcion;
    private int stock;

    public String getId() {
        return this.id;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Talle getTalle() {
        return this.talle;
    }

    public void setTalle(Talle talle) {
        this.talle = talle;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
