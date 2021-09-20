/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Jugador;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends JpaRepository<Jugador, String> {
    
    @Query("select j from Jugador j where j.nacionalidad LIKE :q or j.nombreCompleto LIKE :q or j.posicion.nombre LIKE :q or j.fechaNacimiento LIKE :q or j.peso LIKE :q or j.altura LIKE :q")
    List<Jugador> findAllByQ(@Param("q") String q);

    @Query("select j from Jugador j where j.posicion.nombre = :q")
    List<Jugador> findAllByPosicion(@Param("q") String q);

}
