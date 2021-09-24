package com.proyecto.club.servicios;
import com.proyecto.club.repositorios.NoticiaRepositorio;
import java.util.List;
import java.util.Optional;
import com.proyecto.club.entidades.Noticia;
import com.proyecto.club.excepciones.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NoticiaServicio {
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;

    @Transactional
    public Noticia save(Noticia x) throws WebException {
        if (x.getTitulo()==null || x.getTitulo().isEmpty()) {
           throw new WebException("Falta ingresar el título"); 
        }
        if (x.getDescripcion()==null || x.getDescripcion().isEmpty()) {
            throw new WebException("Falta el cuerpo de la noticia");
        }
        return noticiaRepositorio.save(x);
    }

    @Transactional
    public void delete(Noticia x) throws WebException {
        noticiaRepositorio.delete(x);
    }

    @Transactional
    public void deleteById(String id) {
        Optional<Noticia> op = noticiaRepositorio.findById(id);
        if (op.isPresent()) {
            noticiaRepositorio.delete(op.get());
        }
    }

    @Transactional
    public Noticia modify(String id, String nuevoTitulo, String nuevaDescripcion) {
        Noticia n = noticiaRepositorio.findById(id).get();
        n.setTitulo(nuevoTitulo);
        n.setDescripcion(nuevaDescripcion);
        return noticiaRepositorio.save(n);
    }

    public Optional<Noticia> findById(String id){
            return noticiaRepositorio.findById(id);
    }

    public List<Noticia> listAll(){
        return noticiaRepositorio.findAll();
    }

    public List<Noticia> listByQ(String q){
        return noticiaRepositorio.findByTituloContainingOrDescripcionContaining(q, q);
    }

}
