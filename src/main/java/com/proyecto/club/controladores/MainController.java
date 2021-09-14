
package com.proyecto.club.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author S
 */

@Controller
@RequestMapping("/")
public class mainController {
    
    @GetMapping("/")
    public String inicio(){
    return "index.html";
    }
    
    @GetMapping("/indumentaria")
    public String indumentaria(){
    return "indumentaria.html";
    }
    
    @GetMapping("/productos")
    public String productos(){
    return "productos.html";
    }
    
    @GetMapping("/usuario")
    public String usuarios(){
    return "usuario.html";
    }
    @GetMapping("/socio")
    public String socios(){
    return "socio.html";
    }
    
    @GetMapping("/personal")
    public String personal(){
    return "personal.html";
    }
    
}
