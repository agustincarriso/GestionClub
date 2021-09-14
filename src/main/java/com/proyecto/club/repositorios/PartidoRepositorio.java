package com.proyecto.club.repositorios;

import java.util.List;

import com.proyecto.club.entidades.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Javi
 */
@Repository
public interface PartidoRepositorio extends JpaRepository<Partido, String> {
    // No entiendo por qué no puedo llamar al atributo nombre de local y visitante
    @Query("select x from Partido x where x.arbitro LIKE :q or x.estadio.nombre LIKE :q or x.local LIKE :q or x.visitante LIKE :q") 
    List<Partido> findAllByQ(@Param("q") String q);

}
