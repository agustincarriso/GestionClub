package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Posicion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicionRepositorio extends JpaRepository<Posicion, String> {
    
    @Query("select j from Posicion j where j.nombre LIKE :q")
    List<Posicion> findAll(@Param("q") String q);
}
