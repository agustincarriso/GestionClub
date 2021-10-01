
package com.proyecto.club.entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author S
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Producto implements Serializable {


@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid",strategy = "uuid2")
protected String id;
protected String nombre;
protected String descripcion;
protected Integer stock;
protected Double precio;

@OneToOne
protected Foto foto;

/* protected imagen(veo como se hace y lo agrego)*/

    public Producto() {
    }

    public Producto(String id, String nombre, String descripcion, Integer stock, Double precio, Foto foto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.foto = foto;
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

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
   
    
}
