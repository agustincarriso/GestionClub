package com.proyecto.club.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Partido implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private Equipo local;
    private Equipo visitante;
    private String arbitro;
    @ManyToOne
    private Estadio estadio;
    @Temporal(TemporalType.TIMESTAMP)
    private Date FechaHora;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Equipo getLocal() {
        return this.local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return this.visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public String getArbitro() {
        return this.arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public Estadio getEstadio() {
        return this.estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Date getFechaHora() {
        return this.FechaHora;
    }

    public void setFechaHora(Date FechaHora) {
        this.FechaHora = FechaHora;
    }

   
    
    

    
}
