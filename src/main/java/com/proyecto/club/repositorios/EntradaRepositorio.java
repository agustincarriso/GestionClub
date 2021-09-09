package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Javi
 */
@Repository
public interface EntradaRepositorio extends JpaRepository<Entrada, String>{
    List<Entrada> findByUbicacionContaining(String ubicacion);
}
