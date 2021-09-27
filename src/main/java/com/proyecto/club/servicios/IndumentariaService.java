package com.proyecto.club.servicios;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Indumentaria;
import com.proyecto.club.repositorios.IndumentariaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author S
 */
@Service
public class IndumentariaService {

    @Autowired
    private IndumentariaRepository indumentariaRepository;

    @Transactional
    public Indumentaria save(Indumentaria indumentaria) throws WebException {
        
       /*                            
        if (indumentaria.getId().isEmpty() || indumentaria.getId() == null) {

            throw new WebException("El id no puede ser nulo");

        }

        if (indumentaria.getNombre().isEmpty() || indumentaria.getNombre() == null) {

            throw new WebException("El nombre no puede estar vacio");

        }

        if (indumentaria.getDescripcion().isEmpty() || indumentaria.getDescripcion() == null) {

            throw new WebException("Debe completar la descripcion");

        }

        if (indumentaria.getPrecio() <= 0 || indumentaria.getPrecio() == null) {

            throw new WebException("Debe indicar el precio");

        }

        if (indumentaria.getStock() <= 0 || indumentaria.getStock() == null) {

            throw new WebException("El stock debe ser mayor o igual a cero");

        }

        if (indumentaria.getTalle() == null) {

            throw new WebException("Debe indicar el talle");

        }

        if (indumentaria.getColor() == null) {

            throw new WebException("Debe indicar el color");

        }
        */
       
        Indumentaria aux = new Indumentaria();
        aux.setNombre(indumentaria.getNombre());
        aux.setDescripcion(indumentaria.getDescripcion());
        aux.setPrecio(indumentaria.getPrecio());
        aux.setStock(indumentaria.getStock());
        aux.setColor(indumentaria.getColor());
        aux.setTalle(indumentaria.getTalle());
        
        return indumentariaRepository.save(aux);

    }

    /*@Transactional
    public Indumentaria save(String nombre, String descripcion, Integer stock, Double precio, Talle talle, Color color) {

        Indumentaria indumentaria = new Indumentaria();

        indumentaria.setNombre(nombre);
        indumentaria.setDescripcion(descripcion);
        indumentaria.setStock(Integer.SIZE);
        indumentaria.setPrecio(Double.NaN);
        indumentaria.setTalle(talle);
        indumentaria.setColor(color);

        return indumentariaRepository.save(indumentaria);
        
    }*/  
    public List<Indumentaria> listAll() {
        List<Indumentaria> lista = indumentariaRepository.findAll();
        return lista;
    }

    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Indumentaria> optional = indumentariaRepository.findById(id);
        if (optional.isPresent()) {
            indumentariaRepository.delete(optional.get());

        }else {
            throw new WebException("No se encontra la indumentaria seleccionada");
        }

    }

    @Transactional
    public void deleteByObject(Indumentaria indumentaria) {

        indumentariaRepository.delete(indumentaria);

    }
}
