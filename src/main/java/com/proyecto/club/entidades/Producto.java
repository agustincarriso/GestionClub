
package com.proyecto.club.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author S
 */

@Entity
public class Producto implements Serializable {


@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid",strategy = "uuid2")
private String id;
private String nombre;
private String descripcion;
private Integer stock;
private Double precio;

/* protected imagen(veo como se hace y lo agrego)*/

    public Producto() {
    }

    public Producto(String id, String nombre, String descripcion, Integer stock, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
   
}
