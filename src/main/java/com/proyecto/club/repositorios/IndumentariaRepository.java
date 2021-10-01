
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Indumentaria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface IndumentariaRepository extends JpaRepository<Indumentaria, String>{
    
    
     @Query("select i from Indumentaria i where i.nombre LIKE :query or "
        + "i.descripcion LIKE :query or "
        + "i.stock LIKE :query or "
        + "i.precio LIKE :query or "
        + "i.color LIKE :query or "
        + "i.talle LIKE :query")
     List<Indumentaria> findByQuery(@Param("query") String query);  
    
}
