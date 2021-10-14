package com.proyecto.club.controladores;
import com.proyecto.club.entidades.Jugador;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.servicios.JugadorServicio;
import com.proyecto.club.servicios.PosicionServicio;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugador")
public class JugadorController {
    
    
    @Autowired
    private JugadorServicio jugadorServicio;
    
    @Autowired
    private PosicionServicio posicionServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String lista(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("jugadores", jugadorServicio.listallByQ(query));
        }else{
            model.addAttribute("jugadores", jugadorServicio.listall());
        }
        return "/html-administracion/jugador/jugador-list";
    }	
	
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
        return "/html-administracion/jugador/jugador-form";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarJugador(Model model,RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile imagen,Jugador jugador) throws IOException {
        try {
            jugadorServicio.save(jugador,imagen);
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        jugadorServicio.deleteById(id);
        return "redirect:/jugador/list";
    }
}
