
package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String>{
    
}
