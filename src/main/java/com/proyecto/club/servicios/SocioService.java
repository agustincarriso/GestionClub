
package com.proyecto.club.servicios;

import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.repositorios.SocioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author S
 */

@Service
public class SocioService {
    
     @Autowired
    public SocioRepository socioRepository;

    @Transactional
    public Socio save(Socio socio) throws WebException {
        
        System.out.println("Estoy en servicio aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(socio.getFechaInicio());
       
        
//        if (socio.getId().isEmpty() || socio.getId() == null) {
//
//            throw new WebException("El id no puede ser nulo");}

//        if (socio.getNombre().isEmpty() || socio.getNombre() == null) {
//
//            throw new WebException("El nombre no puede estar vacio");
//        }
//
//        if (socio.getApellido().isEmpty() || socio.getApellido()== null) {
//
//            throw new WebException("El apellido no puede estar vacio");
//        }
//
//        if (socio.getDomicilio().isEmpty() || socio.getDomicilio()== null) {
//
//            throw new WebException("El domicilio no puede estar vacio");
//        }
//
//        if (socio.getEmail().isEmpty() || socio.getEmail()== null) {
//
//            throw new WebException("El email no puede estar vacio");
//        }
//
//         if (socio.getPassword().isEmpty() || socio.getPassword()== null) {
//
//            throw new WebException("El password no puede estar vacio");
//        }
//         
//          if (socio.getDni().isEmpty() || socio.getDni()== null) {
//
//            throw new WebException("El password no puede estar vacio");
//        }
//          
//            if (socio.getTelefono().isEmpty() || socio.getTelefono()== null) {
//
//            throw new WebException("El password no puede estar vacio");
//        }

            return socioRepository.save(socio);

    }

    public List<Socio> listAll() {
        List<Socio> lista = socioRepository.findAll();
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
