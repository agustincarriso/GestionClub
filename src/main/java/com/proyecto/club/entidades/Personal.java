
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
public class Personal extends Usuario {
    

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String cargo; /* Podria ser Enum tambien*/
    
    private Double salario;
    
    @OneToOne
    private Foto foto;
    
    /*@Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;*/

    public Personal() {
    }

    public Personal(String id, String nombre, String apellido, String domicilio, String email, String password, String dni, String telefono, String cargo, Double salario, Foto foto ) {
        super(id, nombre, apellido, domicilio, email, password, dni, telefono,foto);
        this.id = id;
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Foto getFoto() {
        return foto;
    }

    @Override
    public void setFoto(Foto foto) {
        this.foto = foto;
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

    @Override
    public String toString() {
        return "Personal{" + "id=" + id + ", cargo=" + cargo + ", salario=" + salario + '}';
    }

}
