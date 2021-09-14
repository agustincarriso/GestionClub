
package com.proyecto.club.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;



/**
 * @author S
 */

@Entity
public class Indumentaria extends Producto{
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String talle;
    private String color;

    /* private imagen(veo como se hace y lo agrego)*/

    public Indumentaria() {
    }

    
    
    public Indumentaria(String talle, String color, String id, String nombre, String descripcion, Integer stock, Double precio) {
        super(id, nombre, descripcion, stock, precio);
        this.talle = talle;
        this.color = color;
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

    
   
}
