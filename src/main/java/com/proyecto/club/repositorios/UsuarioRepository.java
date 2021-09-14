package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    
    
}
