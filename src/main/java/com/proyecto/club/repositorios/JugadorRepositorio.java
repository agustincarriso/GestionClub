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

    @Query("select p from Persona p where p.nombre LIKE :q or p.apellido LIKE :q"
            + " or p.edad LIKE :q or p.ciudad.nombre LIKE :q")
    List<Jugador> findAllByQ(@Param("q") String q);
}
