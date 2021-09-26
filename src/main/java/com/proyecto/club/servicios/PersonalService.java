
package com.proyecto.club.servicios;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Personal;
import com.proyecto.club.repositorios.PersonalRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author S
 */

@Service
public class PersonalService {

    @Autowired
    public PersonalRepository personalRepository;

    @Transactional
    public Personal save(Personal personal) throws WebException {

        System.out.println(personal.toString());
        
        
//        if (personal.getNombre().isEmpty() || personal.getNombre() == null) {
//
//            throw new WebException("El nombre no puede estar vacio");
//        }
//
//        if (personal.getApellido().isEmpty() || personal.getApellido() == null) {
//
//            throw new WebException("Debe completar el apellido");
//        }
//
//        if (personal.getDomicilio().isEmpty() || personal.getDomicilio() == null) {
//
//            throw new WebException("Debe completar el domicilio");
//        }
//
//        if (personal.getEmail().isEmpty() || personal.getEmail() == null) {
//
//            throw new WebException("Debe completar la email");
//        }
//        
//         if (personal.getPassword().isEmpty() || personal.getPassword()== null) {
//
//            throw new WebException("Debe completar la contraseña");
//        }
//         
//          if (personal.getDni().isEmpty() || personal.getDni()== null) {
//
//            throw new WebException("Debe completar la contraseña");
//        }
//        
//         if (personal.getTelefono().isEmpty() || personal.getTelefono() == null) {
//
//            throw new WebException("Debe completar el telefono");
//        }
//         
//       if (personal.getCargo().isEmpty() || personal.getCargo()== null) {
//
//            throw new WebException("Debe completar el cargo");
//       

       // Falta validacion de salario
        
//       }
       return personalRepository.save(personal);
    }

    public List<Personal> listAll() {
        List<Personal> lista = personalRepository.findAll();
        return lista;
    }
    
    public List<Personal> listByQuery(String query) {
        List<Personal> lista = personalRepository.findByQuery(query);
        return lista;
    }
    public Optional<Personal> findById(String id) {
                
        return  personalRepository.findById(id);
    }
    
    
    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Personal> optional = personalRepository.findById(id);
        if (optional.isPresent()) {
            personalRepository.delete(optional.get());

        } else 
            throw new WebException("No se encontra al personal seleccionado");
        }

    

    @Transactional
    public void deleteByObject(Personal personal){

        personalRepository.delete(personal);

    }



    
}