
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface PersonalRepository extends JpaRepository<Personal, String>{
    
}
