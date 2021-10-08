
package com.proyecto.club.servicios;

import com.proyecto.club.entidades.Foto;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.enums.Role;
import com.proyecto.club.repositorios.SocioRepository;
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
public class SocioService {
    
    @Autowired
    public SocioRepository socioRepository;
    
    @Autowired
    public UsuarioService usuarioService;
    
    @Autowired
    public FotoService fotoService;

    @Transactional
    public Socio save(Socio socio, MultipartFile archivo) throws WebException, IOException {
        
              
        if (socio.getNombre().isEmpty() || socio.getNombre() == null) {

            throw new WebException("El nombre no puede estar vacio");
        }

        if (socio.getApellido().isEmpty() || socio.getApellido()== null) {

            throw new WebException("El apellido no puede estar vacio");
        }

        if (socio.getDomicilio().isEmpty() || socio.getDomicilio()== null) {

            throw new WebException("El domicilio no puede estar vacio");
        }

        if (socio.getEmail().isEmpty() || socio.getEmail()== null) {

            throw new WebException("El email no puede estar vacio");
        }

         if (socio.getPassword().isEmpty() || socio.getPassword()== null) {

            throw new WebException("El password no puede estar vacio");
        }
          Usuario usuario2 = usuarioService.findByDni(socio.getDni());
          if (socio.getDni().isEmpty() || socio.getDni()== null || usuario2 == null || usuario2.getDni() != socio.getDni()) {

            throw new WebException("El dni no puede estar vacio");
        }
          
            if (socio.getTelefono().isEmpty() || socio.getTelefono()== null) {

            throw new WebException("El password no puede estar vacio");
        }
            
            socio.setRol(Role.SOCIO);

            Foto img = fotoService.guardarFoto(archivo);
            socio.setFoto(img);
            
            return socioRepository.save(socio);

    }

    public List<Socio> listAll() {
        List<Socio> lista = socioRepository.findAll();
        return lista;
    }
    
    public List<Socio> findByQuery(String query) {
        List<Socio> lista = socioRepository.findByQuery(query);
        return lista;
    }
    
    public Optional<Socio> findById(String id) {
                
        return  socioRepository.findById(id);
    }
    
    
    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Socio> optional = socioRepository.findById(id);
        if (optional.isPresent()) {
            socioRepository.delete(optional.get());

        } else {
            throw new WebException("No se encontra la socio seleccionada");
        }

    }

    @Transactional
    public void deleteByObject(Socio socio) {

        socioRepository.delete(socio);

    }
    
    
    
}
