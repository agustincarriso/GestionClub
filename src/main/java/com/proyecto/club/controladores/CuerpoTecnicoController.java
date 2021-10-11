package com.proyecto.club.controladores;
import com.proyecto.club.entidades.CuerpoTecnico;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.servicios.CuerpoTecnicoServicio;
import com.proyecto.club.servicios.PuestoCTServicio;
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
@RequestMapping("/cuerpotecnico")
public class CuerpoTecnicoController {
    
    @Autowired
    private CuerpoTecnicoServicio cuerpotecnicoServicio;
    
     @Autowired
    private PuestoCTServicio puestoServicio;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String listarCuerpoTecnicoes(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("cuerpotecnicolista", cuerpotecnicoServicio.listAll(q));
        }else{
            model.addAttribute("cuerpotecnicolista", cuerpotecnicoServicio.listAll());
        }
        return "/html-administracion/cuerpo-tecnico/cuerpotecnico-list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public String crearCuerpoTecnico(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<CuerpoTecnico> optional = cuerpotecnicoServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("cuerpotecnico",optional.get());
            }else {
            return "redirect:/cuerpotecnico/list";
            } 
        }else{
           model.addAttribute("cuerpotecnico",new CuerpoTecnico()); 
        }
         model.addAttribute("puestos", puestoServicio.listAll());
        return "/html-administracion/cuerpo-tecnico/cuerpotecnico-form";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String guardarCuerpoTecnico(Model model,RedirectAttributes redirectAttributes,CuerpoTecnico cuerpotecnico) {
       
        try {
            cuerpotecnicoServicio.save2(cuerpotecnico);
            //redirectAttributes.addFlashAttribute("error", "Primer paso completado exitosamente");  
        } catch (WebException ex) {
            ex.printStackTrace();
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("cuerpotecnico", cuerpotecnico);
            model.addAttribute("puestos", puestoServicio.listAll());
        return "cuerpotecnico-form";
        }
        return "redirect:/cuerpotecnico/list";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        cuerpotecnicoServicio.deleteById(id);
        return "redirect:/cuerpotecnico/list";
    }
}
