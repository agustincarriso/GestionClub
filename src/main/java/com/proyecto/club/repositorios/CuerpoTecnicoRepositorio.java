package com.proyecto.club.repositorios;
import com.proyecto.club.entidades.CuerpoTecnico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuerpoTecnicoRepositorio extends JpaRepository<CuerpoTecnico, String> {
    
    @Query("select c from CuerpoTecnico c where c.nombreCompleto LIKE :q or c.nacionalidad LIKE :q or c.puesto.nombre LIKE :q")
    List<CuerpoTecnico> findAll(@Param("q") String q);
    
    
    @Query("select c from CuerpoTecnico c where c.puesto.nombre = :q")
    List<CuerpoTecnico> findAllByPuesto(@Param("q") String q);

    @Query("select c from CuerpoTecnico c where c.id = :id")
	CuerpoTecnico encontrarPorId(@Param("id") String id);

}
