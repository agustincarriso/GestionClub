package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Javi
 */
@Repository
public interface PartidoRepositorio extends JpaRepository<Partido, String> {
 //   List<Partido> findByArbitroContainingOrEquipoLocalNombreContainingOrEquipoVisitanteNombreContainingOrEstadioNombreContaining(String a, String b, String c, String d);
}
