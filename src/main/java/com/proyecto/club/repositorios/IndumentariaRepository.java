
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Indumentaria;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface IndumentariaRepository extends JpaRepository<Indumentaria, String>{
    
       
    
}
