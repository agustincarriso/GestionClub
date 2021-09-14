package com.proyecto.club.servicios;

import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.entidades.Producto;
import com.proyecto.club.repositorios.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author S
 */
@Service
public class ProductoService {

    @Autowired
    public ProductoRepository productoRepository;

    @Transactional
    public Producto save(Producto producto) throws WebException {


        if (producto.getNombre().isEmpty() || producto.getNombre() == null) {

            throw new WebException("El nombre no puede estar vacio");
        }

        if (producto.getDescripcion().isEmpty() || producto.getDescripcion() == null) {

            throw new WebException("Debe completar la descripcion");
        }

        if (producto.getPrecio() <= 0 || producto.getPrecio() == null) {

            throw new WebException("Debe indicar el precio");
        }

        if (producto.getStock() <= 0 || producto.getStock() == null) {

            throw new WebException("El stock debe ser mayor o igual a cero");
        }

        return productoRepository.save(producto);

    }

    public List<Producto> listAll() {
        List<Producto> lista = productoRepository.findAll();
        return lista;
    }
    public Optional<Producto> findById(String id) {
                
        return  productoRepository.findById(id);
    }
    
    
    @Transactional
    public void deleteById(String id) throws WebException {
        Optional<Producto> optional = productoRepository.findById(id);
        if (optional.isPresent()) {
            productoRepository.delete(optional.get());

        } else {
            throw new WebException("No se encontra la producto seleccionada");
        }

    }

    @Transactional
    public void deleteByObject(Producto producto) {

        productoRepository.delete(producto);

    }

}
    

