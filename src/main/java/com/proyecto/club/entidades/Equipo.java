package com.proyecto.club.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Equipo implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    @OneToMany
    private List<Jugador> jugadores;
    @OneToMany
    private List<CuerpoTecnico> cuerpoTecnico;

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

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<CuerpoTecnico> getCuerpoTecnico() {
        return cuerpoTecnico;
    }

    public void setCuerpoTecnico(List<CuerpoTecnico> cuerpoTecnico) {
        this.cuerpoTecnico = cuerpoTecnico;
    }
    
}