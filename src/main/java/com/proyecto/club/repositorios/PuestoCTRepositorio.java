package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.PuestoCT;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PuestoCTRepositorio extends JpaRepository<PuestoCT, String> {
    
    @Query("select p from PuestoCT p where p.nombre LIKE :q")
    List<PuestoCT> findAll(@Param("q") String q);
}
