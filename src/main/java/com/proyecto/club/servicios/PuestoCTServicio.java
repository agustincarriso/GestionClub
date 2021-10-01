package com.proyecto.club.servicios;
import com.proyecto.club.entidades.PuestoCT;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.repositorios.PuestoCTRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PuestoCTServicio {
    
    
    @Autowired
    private PuestoCTRepositorio puestoRepositorio;
    
    @Autowired
    private CuerpoTecnicoServicio cuerpoTecnicoServicio;

    public PuestoCT save(String nombre) {
        PuestoCT puesto = new PuestoCT();
        puesto.setNombre(nombre);
        return puestoRepositorio.save(puesto);
    }

    public PuestoCT save2(PuestoCT puesto) throws WebException {
      if (puesto.getNombre().isEmpty() || puesto.getNombre() == null) {
            throw new WebException("El nombre del puesto no puede ser nulo");
        }
        return puestoRepositorio.save(puesto);
    }


    public List<PuestoCT> listAll() {
         List<PuestoCT> lista = puestoRepositorio.findAll();
         return lista;
    }

    public List<PuestoCT> listAll(String q) {
        return puestoRepositorio.findAll("%" + q + "%");
    }

    public Optional<PuestoCT> findById(String id) {
        return puestoRepositorio.findById(id);
    }

    public PuestoCT findById(PuestoCT puesto) {
        Optional<PuestoCT> optional1 = puestoRepositorio.findById(puesto.getId());
            if (optional1.isPresent()) {
                puesto = optional1.get();
            }
        return puesto;
    }
    
    @Transactional
    public void delete(PuestoCT puesto) {
        puestoRepositorio.delete(puesto);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<PuestoCT> optional = puestoRepositorio.findById(id);
        if (optional.isPresent()) {
            PuestoCT puesto = optional.get();
            cuerpoTecnicoServicio.deleteFieldPuesto(puesto);
            puestoRepositorio.delete(puesto);
        }

    }
}
