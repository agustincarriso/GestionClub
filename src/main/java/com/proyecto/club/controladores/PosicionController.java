package com.proyecto.club.controladores;
import com.proyecto.club.entidades.Posicion;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.servicios.PosicionServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/posicion")
public class PosicionController {
    
    @Autowired
    private PosicionServicio posicionServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listarPosiciones(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("posiciones", posicionServicio.listAll(q));
        }else{
            model.addAttribute("posiciones", posicionServicio.listAll());
        }
        return "/html-administracion/posicion/posicion-list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearPosicion(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Posicion> optional = posicionServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("posicion",optional.get());
            }else {
            return "redirect:/posicion/list";
            } 
        }else{
           model.addAttribute("posicion",new Posicion()); 
        }
        return "/html-administracion/posicion/posicion-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarPosicion(RedirectAttributes redirectAttributes,Posicion posicion) {
        
        try {
            posicionServicio.save2(posicion);
            redirectAttributes.addFlashAttribute("success", "Posicion guardada con exito");  
        } catch (WebException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());        }
        return "redirect:/posicion/list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        posicionServicio.deleteById(id);
        return "redirect:/posicion/list";
    }
}
