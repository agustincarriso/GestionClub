
package com.proyecto.club.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author S
 */

@Entity
public class Socio extends Usuario implements Serializable {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String numeroSocio; // String porque no lo usamos para calculos
    private String acceso; //poner enum popular,platea cubierta,platea descubierta (le podemos poner tipoSocio mepa)
    private Double valorCuota;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;

    public Socio(String numeroSocio, String acceso, Double valorCuota, Date fechaInicio, Date fechaBaja, String nombre, String apellido, String domicilio, String email, String password, String dni, String telefono) {
        super(nombre, apellido, domicilio, email, password, dni, telefono);
        this.numeroSocio = numeroSocio;
        this.acceso = acceso;
        this.valorCuota = valorCuota;
        this.fechaInicio = fechaInicio;
        this.fechaBaja = fechaBaja;
    }

    

    public void setId(String id) {
        this.id = id;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public Double getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(Double valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

   

}
