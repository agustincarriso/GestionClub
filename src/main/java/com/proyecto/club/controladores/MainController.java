package com.proyecto.club.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController {
    
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
    
    @GetMapping("/posicion")
    public String posicion(){
    return "posicion.html";
    }
    
    @GetMapping("/jugador")
    public String jugador(){
    return "jugador.html";
    }
    
    @GetMapping("/cuerpotecnico")
    public String cuerpotecnico(){
    return "cuerpotecnico.html";
    }
    
     @GetMapping("/puestoct")
    public String puesto(){
    return "puestoct.html";
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
