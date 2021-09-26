
package com.proyecto.club.servicios;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Foto;
import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.repositorios.UsuarioRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author S
 */

@Service
public class UsuarioService {
    
    @Autowired
    public UsuarioRepository usuarioRepository;
    
    @Autowired
    public FotoService fotoService;

    @Transactional
    public Usuario save(Usuario usuario, MultipartFile archivo) throws WebException, IOException {
        
        
        if (usuario.getNombre().isEmpty() || usuario.getNombre() == null) {

            throw new WebException("El nombre no puede estar vacio");
        }

        if (usuario.getApellido().isEmpty() || usuario.getApellido()== null) {

            throw new WebException("El apellido no puede estar vacio");
        }

        if (usuario.getDomicilio().isEmpty() || usuario.getDomicilio()== null) {

            throw new WebException("El domicilio no puede estar vacio");
        }

        if (usuario.getEmail().isEmpty() || usuario.getEmail()== null) {

            throw new WebException("El email no puede estar vacio");
        }

         if (usuario.getPassword().isEmpty() || usuario.getPassword()== null) {

            throw new WebException("El password no puede estar vacio");
        }
         
          if (usuario.getDni().isEmpty() || usuario.getDni()== null) {

            throw new WebException("El password no puede estar vacio");
        }
          
            if (usuario.getTelefono().isEmpty() || usuario.getTelefono()== null) {

            throw new WebException("El password no puede estar vacio");
        }

            Foto img = fotoService.guardarFoto(archivo);
            
            usuario.setFoto(img);
            
            System.out.println( "id: " + usuario.getId() + "     aaaaaaaaaaaaaaaaa");
            return usuarioRepository.save(usuario);

    }

    public List<Usuario> listAll() {
        List<Usuario> lista = usuarioRepository.findAll();
        return lista;
    }
    
    public List<Usuario> findByQuery(String query) {
        List<Usuario> lista = usuarioRepository.findByQuery(query);
        return lista;
    }
    
    
    public Optional<Usuario> findById(String id) {
                
        return  usuarioRepository.findById(id);
    }
    
    
    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isPresent()){
            usuarioRepository.delete(optional.get());

        } else {
            throw new WebException("No se encontra la usuario seleccionada");
        }

    }

    @Transactional
    public void deleteByObject(Usuario usuario) {

        usuarioRepository.delete(usuario);

    }

}
    


    

