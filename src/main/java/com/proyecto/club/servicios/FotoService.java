package com.proyecto.club.servicios;

import com.proyecto.club.entidades.Foto;
import com.proyecto.club.repositorios.FotoRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author S
 */
@Service
public class FotoService {

    @Autowired
    private FotoRepository fotoRepository;

    public Foto guardarFoto(MultipartFile archivo) throws IOException {

        if (archivo != null) {

            try {
                Foto foto = new Foto();
                foto.setNombre(archivo.getName());
                foto.setMime(archivo.getContentType());
                foto.setContenido(archivo.getBytes());

                fotoRepository.save(foto);
                return foto;
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
