
package com.proyecto.club.entidades;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;


/**
 * @author S
 */

@Entity
public class Personal extends Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String cargo;
    /* Podria ser Enum tambien*/
    private Double salario;

    /*@Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;*/

    public Personal() {
    }

    public Personal(String cargo, Double salario, String nombre, String apellido, String domicilio, String email, String password, String dni, String telefono) {
        super(nombre, apellido, domicilio, email, password, dni, telefono);
        this.cargo = cargo;
        this.salario = salario;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

}
