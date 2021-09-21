package com.proyecto.club.servicios;
import java.util.List;
import java.util.Optional;
import com.proyecto.club.entidades.Equipo;
import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.repositorios.EquipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EquipoServicio {
    
    @Autowired
    private EquipoRepositorio equipoRepositorio;

    @Transactional
    public Equipo save(Equipo x) throws WebException {
        if (x.getNombre()==null || x.getNombre().isEmpty()) {
            throw new WebException("Falta el nombre del equipo");
        }
        return equipoRepositorio.save(x);
    }

    @Transactional
    public void delete(Equipo x) {
        equipoRepositorio.delete(x);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Equipo> op = equipoRepositorio.findById(id);
        if (op.isPresent()) {
            equipoRepositorio.delete(op.get());
        }
    }

    @Transactional
    public Equipo modify(String id, String nombreNuevo) {
        Equipo x = equipoRepositorio.getById(id);
        x.setNombre(nombreNuevo);
        return equipoRepositorio.save(x);
    }

    public List<Equipo> listAll() {
        return equipoRepositorio.findAll();
    }

    public List<Equipo> listByQ(String q) {
        return equipoRepositorio.findByNombreContaining(q);
    }

    public Equipo findById(String id) {
        return equipoRepositorio.getById(id);
    }
}
