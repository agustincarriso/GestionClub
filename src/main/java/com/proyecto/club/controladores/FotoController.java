package com.proyecto.club.controladores;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author S
 */
@Controller
@RequestMapping("/foto")
public class FotoController {
    
    @Autowired
    public UsuarioService usuarioService;
    
    @GetMapping("/socio")
    public ResponseEntity<byte[]> fotoUsuario(@RequestParam String id) throws WebException{
        try {
            Usuario usuario = usuarioService.encontrarPorId(id);
            byte[] foto = usuario.getFoto().getContenido();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger(FotoController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
}
