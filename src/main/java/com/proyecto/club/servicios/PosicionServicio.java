package com.proyecto.club.servicios;
import com.proyecto.club.entidades.Posicion;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.repositorios.PosicionRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PosicionServicio {
    
    
    @Autowired
    private PosicionRepositorio posicionRepositorio;
    
    @Autowired
    private JugadorServicio jugadorServicio;

    public Posicion save(String nombre) {
        Posicion posicion = new Posicion();
        posicion.setNombre(nombre);
        return posicionRepositorio.save(posicion);
    }

    public Posicion save2(Posicion posicion) throws WebException {
        if (posicion.getNombre().isEmpty() || posicion.getNombre() == null) {
            throw new WebException("El nombre de la posicion no puede ser nulo");
        }
        return posicionRepositorio.save(posicion);
    }


    public List<Posicion> listAll() {
         List<Posicion> lista = posicionRepositorio.findAll();
         return lista;
    }

    public List<Posicion> listAll(String q) {
        return posicionRepositorio.findAll("%" + q + "%");
    }

    public Optional<Posicion> findById(String id) {
        return posicionRepositorio.findById(id);
    }

    public Posicion findById(Posicion posicion) {
        Optional<Posicion> optional1 = posicionRepositorio.findById(posicion.getId());
            if (optional1.isPresent()) {
                posicion = optional1.get();
            }
        return posicion;
    }
    
    @Transactional
    public void delete(Posicion posicion) {
        posicionRepositorio.delete(posicion);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Posicion> optional = posicionRepositorio.findById(id);
        if (optional.isPresent()) {
            Posicion posicion = optional.get();
            jugadorServicio.deleteFieldPosicion(posicion);
            posicionRepositorio.delete(posicion);
        }

    }
}
