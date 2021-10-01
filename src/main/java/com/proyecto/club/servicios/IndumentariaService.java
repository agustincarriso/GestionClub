package com.proyecto.club.servicios;
import com.proyecto.club.entidades.Foto;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Indumentaria;
import com.proyecto.club.repositorios.IndumentariaRepository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author S
 */

@Service
public class IndumentariaService {

    @Autowired
    private IndumentariaRepository indumentariaRepository;
    
    @Autowired
    private FotoService fotoService;

    @Transactional
    public Indumentaria save(Indumentaria indumentaria, MultipartFile archivo) throws WebException, IOException {
        
      

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

        
        Foto img = fotoService.guardarFoto(archivo);
        
        indumentaria.setFoto(img);
       
        return indumentariaRepository.save(indumentaria);

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
    
       public List<Indumentaria> findByQuery(String query) {
        List<Indumentaria> lista = indumentariaRepository.findByQuery(query);
        return lista;
    }
       
       
    public Optional<Indumentaria> findById(String id) {
                
        return  indumentariaRepository.findById(id);
    }
    
    
    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Indumentaria> optional = indumentariaRepository.findById(id);
        if (optional.isPresent()) {
            indumentariaRepository.delete(optional.get());

        } else {
            throw new WebException("No se encontra la indumentaria seleccionada");
        }

    }

    @Transactional
    public void deleteByObject(Indumentaria indumentaria) {

        indumentariaRepository.delete(indumentaria);

    }
}
