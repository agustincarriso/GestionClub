package com.proyecto.club.servicios;

import java.util.List;
import java.util.Optional;

import com.proyecto.club.entidades.CuerpoTecnico;
import com.proyecto.club.enums.CuerpoTec;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.repositorios.CuerpoTecnicoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Javi
 */
@Service
public class CuerpoTecnicoServicio {

    @Autowired
    private CuerpoTecnicoRepositorio cuerpoTecnicoRepositorio;

    @Transactional
    public CuerpoTecnico save(CuerpoTecnico x) throws WebException {
        if (x.getNombreCompleto()==null || x.getNombreCompleto().isEmpty()) {
            throw new WebException("Falta completar el nombre");
        }
        if (Integer.valueOf(x.getEdad())==null){
            throw new WebException("Falta completar la edad");
        }
        if (x.getNacionalidad()==null || x.getNacionalidad().isEmpty()) {
            throw new WebException("Falta completar la nacionalidad");
        }
        if (x.getPuesto()==null) {
            throw new WebException("Falta completar el puesto");
        }
        return cuerpoTecnicoRepositorio.save(x);
    }

    @Transactional
    public void delete(CuerpoTecnico x) {
        cuerpoTecnicoRepositorio.delete(x);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<CuerpoTecnico> op = cuerpoTecnicoRepositorio.findById(id);
        if (op.isPresent()) {
            cuerpoTecnicoRepositorio.delete(op.get());
        }
    }

    @Transactional
    public CuerpoTecnico modify(String id, String nombreCompleto, int edad, String nacionalidad, CuerpoTec puesto){
        CuerpoTecnico x = cuerpoTecnicoRepositorio.findById(id).get();
        x.setEdad(edad);
        x.setNacionalidad(nacionalidad);
        x.setNombreCompleto(nombreCompleto);
        x.setPuesto(puesto);
        return cuerpoTecnicoRepositorio.save(x);
    }

    public List<CuerpoTecnico> listAll(){
        return cuerpoTecnicoRepositorio.findAll();
    }

    public List<CuerpoTecnico> listByQ(String q){
        return cuerpoTecnicoRepositorio.findByNombrecompletoContainingOrNacionalidadContainingOrPuestoContaining(q, q, q);
    }

    public CuerpoTecnico findById(String id){
        return cuerpoTecnicoRepositorio.findById(id).get();
    }

}
