package com.proyecto.club.repositorios;

import com.proyecto.club.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author S
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	@Query("select p from Usuario p where p.nombre LIKE :query or "
			+ "p.apellido LIKE :query or "
			+ "p.domicilio LIKE :query or "
			+ "p.email LIKE :query or "
			+ "p.password LIKE :query or "
			+ "p.dni LIKE :query or "
			+ "p.telefono LIKE :query ")
	List<Usuario> findAllByQ(@Param("query") String query);

	@Query("select u from Usuario u where u.dni = :dni")
	Usuario findByDni(@Param("dni") String dni);

	@Query("select u from Usuario u where u.email = :email")
	Usuario findByEmail(@Param("email") String email);

	@Query("select u from Usuario u where u.id = :id")
	Usuario encontrarPorId(@Param("id") String id);
}
