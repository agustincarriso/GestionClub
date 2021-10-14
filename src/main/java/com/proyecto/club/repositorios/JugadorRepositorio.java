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
    
    @Query("select j from Jugador j where j.nacionalidad LIKE :query or j.nombreCompleto LIKE :query or j.posicion.nombre LIKE :query or j.fechaNacimiento LIKE :query or j.peso LIKE :query or j.altura LIKE :query")
    List<Jugador> findAllByQ(@Param("query") String query);

    @Query("select j from Jugador j where j.posicion.nombre = :q")
    List<Jugador> findAllByPosicion(@Param("q") String q);

    @Query("select j from Jugador j where j.id = :id")
	Jugador encontrarPorId(@Param("id") String id);

    @Override
    @Query("select j from Jugador j order by j.nombreCompleto")
	List<Jugador> findAll();

}
