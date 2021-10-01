
package com.proyecto.club.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;



/**
 * @author S
 */

@Entity
public class Indumentaria extends Producto {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String talle;
    private String color;
    
    @OneToOne
    private Foto foto;

    /* private imagen(veo como se hace y lo agrego)*/

    public Indumentaria() {
    }

    
    
    public Indumentaria(String id, String nombre, String descripcion, Integer stock, Double precio, String color, String talle, Foto foto) {
        super(id, nombre, descripcion, stock, precio, foto);
        this.talle = talle;
        this.color = color;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    
   
}
