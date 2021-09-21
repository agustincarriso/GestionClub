package com.proyecto.club.servicios;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import com.proyecto.club.entidades.Equipo;
import com.proyecto.club.entidades.Estadio;
import com.proyecto.club.entidades.Partido;
import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.repositorios.PartidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PartidoServicio {
    
    @Autowired
    private PartidoRepositorio partidoRepositorio;

    @Transactional
    public Partido save(Partido x) throws WebException {
        if (x.getEstadio()==null) {
            throw new WebException("Falta el estadio");
        }
        if (x.getArbitro()==null || x.getArbitro().isEmpty()) {
            throw new WebException("Falta el Árbitro");
        }
        if (x.getLocal()==null) {
            throw new WebException("Falta el Equipo Local");
        }
        if (x.getVisitante()==null) {
            throw new WebException("Falta el Equipo Visitante");
        }
        if (x.getEstadio()==null) {
            throw new WebException("Falta el Estadio");
        }
        if (x.getFechaHora()==null) {
            throw new WebException("Falta la fecha y hora del partido");
        }
        return partidoRepositorio.save(x);
    }

    @Transactional
    public void delete(Partido x) {
        partidoRepositorio.delete(x);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Partido> op = partidoRepositorio.findById(id);
        if (op.isPresent()) {
            partidoRepositorio.delete(op.get());
        }
    }

    @Transactional
    public Partido modify(String id, String arbitro, Equipo equipoLocal, Equipo equipoVisitante, Estadio estadio, Date FechaHora) {
        Partido x = partidoRepositorio.getById(id);
        x.setArbitro(arbitro);
        x.setLocal(equipoLocal);
        x.setVisitante(equipoVisitante);
        x.setEstadio(estadio);
        x.setFechaHora(FechaHora);
        return partidoRepositorio.save(x);
    }

    public List<Partido> listAll() {
        return partidoRepositorio.findAll();
    }

   public List<Partido> listByQ(String q) {
       return partidoRepositorio.findAllByQ(q);
   }

    public Partido findById(String id) {
        return partidoRepositorio.findById(id).get();
    }

}
