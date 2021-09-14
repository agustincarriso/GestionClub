package com.proyecto.club.servicios;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.repositorios.EntradaRepositorio;

import java.util.List;
import java.util.Optional;

import com.proyecto.club.entidades.Entrada;
import com.proyecto.club.enums.Ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Javi
 */
@Service
public class EntradaServicio {

    @Autowired
    private EntradaRepositorio entradaRepositorio;

    @Transactional
    public Entrada save(Entrada x) throws WebException {
        if (x.getUbicacion()==null) {
            throw new WebException("Falta la ubicación");
        }
        switch (x.getUbicacion()) {
            case POPULAR:
                x.setPrecio(800d);
                break;
            case PLATEAㅤDESCUBIERTA:
                x.setPrecio(1200d);
                break;
            case PLATEAㅤCUBIERTA:
                x.setPrecio(2000d);
                break;
        }
        return entradaRepositorio.save(x);
    }

    @Transactional
    public void delete(Entrada x) {
        entradaRepositorio.delete(x);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Entrada> op = entradaRepositorio.findById(id);
        if (op.isPresent()) {
            entradaRepositorio.delete(op.get());
        }
    }

    @Transactional
    public Entrada modify(String id, Ubicacion ubicacion) {
        Entrada x = entradaRepositorio.findById(id).get();
        x.setUbicacion(ubicacion);
        switch (x.getUbicacion()) {
            case POPULAR:
                x.setPrecio(800d);
                break;
            case PLATEAㅤDESCUBIERTA:
                x.setPrecio(1200d);
                break;
            case PLATEAㅤCUBIERTA:
                x.setPrecio(2000d);
                break;
        }
        return entradaRepositorio.save(x);
    }

    public List<Entrada> listAll(){
       return entradaRepositorio.findAll();
    }

    public Entrada findById(String id) {
        return entradaRepositorio.getById(id);
    }

    public List<Entrada> listByQ(String q) {
        return entradaRepositorio.findByUbicacionContaining(q);
    }

}
