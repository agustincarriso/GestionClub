package com.proyecto.club.controladores;

import com.proyecto.club.entidades.Jugador;
import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.servicios.JugadorServicio;
import com.proyecto.club.servicios.PosicionServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
    
    
    @Autowired
    private JugadorServicio jugadorServicio;
    
    @Autowired
    private PosicionServicio posicionServicio;

    
    @GetMapping("/list")
    public String listarJugadors(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("jugadores", jugadorServicio.listallByQ(q));
        }else{
            model.addAttribute("jugadores", jugadorServicio.listall());
        }
        return "jugador-list";
    }

    @GetMapping("/form")
    public String crearJugador(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Jugador> optional = jugadorServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("jugador",optional.get());
            }else {
            return "redirect:/jugador/list";
            } 
        }else{
           model.addAttribute("jugador",new Jugador()); 
        }
        model.addAttribute("posiciones", posicionServicio.listAll());
        return "jugador-form";
    }

    @PostMapping("/save")
    public String guardarJugador(Model model,RedirectAttributes redirectAttributes,Jugador jugador) {
        System.out.println("-.-prueba controlador post");
        try {
            jugadorServicio.save(jugador);
            //redirectAttributes.addFlashAttribute("error", "Primer paso completado exitosamente");  
        } catch (WebException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("jugador", jugador);
            model.addAttribute("posiciones", posicionServicio.listAll());
        return "jugador-form";
        }
        return "redirect:/jugador/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        jugadorServicio.deleteById(id);
        return "redirect:/jugador/list";
    }
}
