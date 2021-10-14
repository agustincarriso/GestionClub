
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Socio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface SocioRepository extends JpaRepository<Socio, String>{
    
    @Query("select p from Socio p where p.nombre LIKE :query or "
        + "p.apellido LIKE :query or "
        + "p.domicilio LIKE :query or "
        + "p.email LIKE :query or "
        + "p.password LIKE :query or "
        + "p.dni LIKE :query or "
        + "p.telefono LIKE :query or "
        + "p.numeroSocio LIKE :query or "
        + "p.acceso LIKE :query or "
        + "p.valorCuota LIKE :query or "
        + "p.fechaInicio LIKE :query or "
        + "p.fechaBaja LIKE :query")
    List<Socio> findAllByQ(@Param("query") String query); 
}
