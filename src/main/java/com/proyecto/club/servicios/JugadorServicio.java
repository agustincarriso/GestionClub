package com.proyecto.club.servicios;

import com.proyecto.club.entidades.Jugador;
import com.proyecto.club.entidades.Posicion;
import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.repositorios.JugadorRepositorio;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorServicio {
    
    @Autowired
    private JugadorRepositorio jugadorRepositorio;
    
    @Autowired
    private PosicionServicio posicionServicio;

    public Jugador save(Jugador jugador) throws WebException{
        if (jugador.getNacionalidad() == null) {
             throw new WebException("Debe ingresar la nacionalidad");
        }
        if (jugador.getNombreCompleto().isEmpty() || jugador.getNombreCompleto()== null) {
            throw new WebException("El nombre no puede estar vacio");
        }
        if (jugador.getPosicion() == null) {
            throw new WebException("La posicion no puede ser nula");
        }else{
            jugador.setPosicion(posicionServicio.findById(jugador.getPosicion()));
        }
        if (jugador.getFechaNacimiento() == null) {
            throw new WebException("Debe ingresar la fecha de nacimiento");}
       /* 
        if (jugador.getFechaNacimiento() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fecha = sdf.format(jugador.getFechaNacimiento());
            jugador.setFechaNacimiento(fecha);
        }*/
        
        if (jugador.getPeso() == null) {
            throw new WebException("El nombre no puede estar vacio");
        }
        if (jugador.getAltura() == null) {
            throw new WebException("El nombre no puede estar vacio");
        }
       
        return jugadorRepositorio.save(jugador);
    }
    
    @Transactional
    public Jugador save2(String nacionalidad, String nombreCompleto, Posicion posicion, Date fechaNacimiento, Double peso, Double altura){
        Jugador jugador = new Jugador();
        jugador.setNacionalidad(nacionalidad);
        jugador.setNombreCompleto(nombreCompleto);
        jugador.setPosicion(posicion);
        //jugador.setFechaNacimiento(fechaNacimiento);
        jugador.setPeso(peso);
        jugador.setAltura(altura);
        
        return jugadorRepositorio.save(jugador);
    }
    
    public List<Jugador> listall(){
        List<Jugador> lista = jugadorRepositorio.findAll();
        return lista;
    }
    
    public List<Jugador> listallByPosicion(String nombre){
        return jugadorRepositorio.findAllByPosicion(nombre);
    }
    
    public List<Jugador> listallByQ(String q){
        return jugadorRepositorio.findAllByQ("%"+q+"%");
    }
    
    
    public Optional<Jugador> findById(String id){
        return jugadorRepositorio.findById(id);
    }
    
    @Transactional
    public void delete(Jugador jugador){
        jugadorRepositorio.delete(jugador);
    }
    
    @Transactional
    public void deleteFieldPosicion(Posicion posicion){
        List<Jugador> jugador = jugadorRepositorio.findAllByPosicion(posicion.getNombre());
        for (Jugador jugador1 : jugador) {
            jugador1.setPosicion(null);
        }
        jugadorRepositorio.saveAll(jugador);
    }
    
    @Transactional
    public void deleteById(String id){
        Optional<Jugador> optional = jugadorRepositorio.findById(id);
        if (optional.isPresent()) {
            jugadorRepositorio.delete(optional.get());
        }
    }
    
    
}
