
package com.proyecto.club.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author S
 */

@Entity
public class Socio extends Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Integer numeroSocio;
    private String acceso;
    private Double valorCuota;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaBaja;

    @OneToOne
    private Foto foto;

    public Socio() {

    }

    public Socio(String id, String nombre, String apellido, String domicilio, String email, String password, String dni,
            String telefono, Integer numeroSocio, String acceso, Double valorCuota, Date fechaInicio, Date fechaBaja,
            Foto foto) {
        super(id, nombre, apellido, domicilio, email, password, dni, telefono, foto);
        this.numeroSocio = numeroSocio;
        this.acceso = acceso;
        this.valorCuota = valorCuota;
        this.fechaInicio = fechaInicio;
        this.fechaBaja = fechaBaja;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Integer getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(Integer numeroSocio) {
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
