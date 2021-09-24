
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface PersonalRepository extends JpaRepository<Personal, String>{
   
    @Query("select p from Personal p where p.nombre LIKE :query or "
        + "p.apellido LIKE :query or "
        + "p.domicilio LIKE :query or "
        + "p.email LIKE :query or "
        + "p.password LIKE :query or "
        + "p.dni LIKE :query or "
        + "p.telefono LIKE :query or "
        + "p.cargo LIKE :query or "
        + "p.salario LIKE :query")
     List<Personal> findByQuery(@Param("query") String query); 
}
