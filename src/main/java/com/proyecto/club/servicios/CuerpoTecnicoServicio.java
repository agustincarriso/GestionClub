package com.proyecto.club.servicios;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.CuerpoTecnico;
import com.proyecto.club.entidades.PuestoCT;
import com.proyecto.club.repositorios.CuerpoTecnicoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuerpoTecnicoServicio {
    
    
    @Autowired
    private CuerpoTecnicoRepositorio cuerpotecnicoRepositorio;


    public CuerpoTecnico save(String nombreCompleto, String nacionalidad,Date edad, PuestoCT puesto ) {
        CuerpoTecnico cuerpoTecnico = new CuerpoTecnico();
        
        cuerpoTecnico.setNombreCompleto(nombreCompleto);
        cuerpoTecnico.setNacionalidad(nacionalidad);
        cuerpoTecnico.setEdad(edad);
        cuerpoTecnico.setPuesto(puesto);
        
        return cuerpotecnicoRepositorio.save(cuerpoTecnico);
    }

    public CuerpoTecnico save2(CuerpoTecnico cuerpotecnico) throws WebException {
        if (cuerpotecnico.getNombreCompleto().isEmpty() || cuerpotecnico.getNombreCompleto()== null) {
            throw new WebException("Debe ingresar un nombre");
        }
        if (cuerpotecnico.getNacionalidad().isEmpty() || cuerpotecnico.getNacionalidad()== null) {
            throw new WebException("Debe ingresar la nacionalidad");
        }
        if (cuerpotecnico.getEdad() == null) {
            throw new WebException("Ingrese el dia de nacimiento");
        }
        return cuerpotecnicoRepositorio.save(cuerpotecnico);
    }


    public List<CuerpoTecnico> listAll() {
         List<CuerpoTecnico> lista = cuerpotecnicoRepositorio.findAll();
         return lista;
    }

    public List<CuerpoTecnico> listAll(String q) {
        return cuerpotecnicoRepositorio.findAll("%" + q + "%");
    }

    public Optional<CuerpoTecnico> findById(String id) {
        return cuerpotecnicoRepositorio.findById(id);
    }

    public CuerpoTecnico findById(CuerpoTecnico cuerpotecnico) {
        Optional<CuerpoTecnico> optional1 = cuerpotecnicoRepositorio.findById(cuerpotecnico.getId());
            if (optional1.isPresent()) {
                cuerpotecnico = optional1.get();
            }
        return cuerpotecnico;
    }
    
    @Transactional
    public void delete(CuerpoTecnico cuerpotecnico) {
        cuerpotecnicoRepositorio.delete(cuerpotecnico);
    }

    @Transactional
    public void deleteFieldPuesto(PuestoCT puesto){
        List<CuerpoTecnico> cuerpoTecnico = cuerpotecnicoRepositorio.findAllByPuesto(puesto.getNombre());
        for (CuerpoTecnico cuerpoTecnico1 : cuerpoTecnico) {
            cuerpoTecnico1.setPuesto(null);
        }
        cuerpotecnicoRepositorio.saveAll(cuerpoTecnico);
    }
    
    @Transactional
    public void deleteById(String id) {
        Optional<CuerpoTecnico> optional = cuerpotecnicoRepositorio.findById(id);
        if (optional.isPresent()) {
            CuerpoTecnico cuerpotecnico = optional.get();
            cuerpotecnicoRepositorio.delete(cuerpotecnico);
        }

    }

    public CuerpoTecnico encontrarPorId(String id) {   
        return  cuerpotecnicoRepositorio.encontrarPorId(id);}

}
