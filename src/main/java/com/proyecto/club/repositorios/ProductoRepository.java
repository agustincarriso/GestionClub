
package com.proyecto.club.repositorios;


import com.proyecto.club.entidades.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */

@Repository
public interface ProductoRepository extends JpaRepository<Producto, String>{
    
     @Query("select i from Producto i where i.nombre LIKE :query or "
        + "i.descripcion LIKE :query or "
        + "i.stock LIKE :query or "
        + "i.precio LIKE :query")
       
     List<Producto> findByQuery(@Param("query") String query);  
    
    
}
