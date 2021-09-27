package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Javi
 */
@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, String> {
    List<Equipo> findByNombreContaining(String nombre);
}
