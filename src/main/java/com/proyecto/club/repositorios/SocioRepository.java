
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface SocioRepository extends JpaRepository<Socio, String>{
    
}
