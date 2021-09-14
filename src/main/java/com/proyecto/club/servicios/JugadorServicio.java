/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.servicios;

import com.proyecto.club.entidades.Jugador;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.repositorios.JugadorRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JugadorServicio {
    @Autowired
    private JugadorRepositorio jugadorRepositorio;
    
    
    public Jugador save(Jugador jugador) throws WebException{
        if (jugador.getNacionalidad().isEmpty() || jugador.getNacionalidad() == null) {
            throw new WebException("La nacionalidad no puede estar vacia");
        }
        if (jugador.getNombreCompleto().isEmpty() || jugador.getNombreCompleto() == null) {
            throw new WebException("El nombre no puede estar vacio");
        }
        if (jugador.getFechaNacimiento() == null || jugador.getFechaNacimiento() == null) {
            throw new WebException("Debe ingresar la fecha de nacimiento");
        }
        if (jugador.getPeso() == null || jugador.getPeso() < 1) {
            throw new WebException("El peso o ser menos a 1");
        }
        if (jugador.getAltura() == null || jugador.getAltura() < 1) {
            throw new WebException("El peso o ser menos a 1");
        }
        return jugadorRepositorio.save(jugador);
    }
/*    
    @Transactional
    public Jugador save(String nacionalidad, String nombreCompleto, Date fechaNacimiento){
        Jugador jugador = new Jugador();
        jugador.setNacionalidad(nacionalidad);
        jugador.setFechaNacimiento(fechaNacimiento);
        jugador.setEdad(edad);
        
        return jugadorRepositorio.save(jugador);
    }
  */  
    public List<Jugador> listall(){
        return jugadorRepositorio.findAll();
    }
    
   /* public List<Jugador> listallByCiudad(String nombre){
        return jugadorRepositorio.findAllByCiudad(nombre);
    }
    
   
    public Jugador findByDni(String dni){
        return jugadorRepositorio.findAllByDni(dni);
    }
    */
    
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
    public void deleteById(String id){
        Optional<Jugador> optional = jugadorRepositorio.findById(id);
        if (optional.isPresent()) {
            jugadorRepositorio.delete(optional.get());
        }
    }
    
}
