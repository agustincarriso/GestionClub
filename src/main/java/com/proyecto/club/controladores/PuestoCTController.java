package com.proyecto.club.controladores;
import com.proyecto.club.entidades.PuestoCT;
import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.servicios.PuestoCTServicio;
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
@RequestMapping("/puesto")
public class PuestoCTController {
    
    @Autowired
    private PuestoCTServicio puestoServicio;

    @GetMapping("/list")
    public String listarPuestos(Model model,@RequestParam(required = false) String q) {
        if (q != null) {
            model.addAttribute("puestos", puestoServicio.listAll(q));
        }else{
            model.addAttribute("puestos", puestoServicio.listAll());
        }
        return "puesto-list";
    }

    @GetMapping("/form")
    public String crearPuestoCT(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<PuestoCT> optional = puestoServicio.findById(id);
            if (optional.isPresent()) {
                model.addAttribute("puesto",optional.get());
            }else {
            return "redirect:/puesto/list";
            } 
        }else{
           model.addAttribute("puesto",new PuestoCT()); 
        }
        return "puesto-form";
    }

    @PostMapping("/save")
    public String guardarPuesto(RedirectAttributes redirectAttributes,PuestoCT puesto) {
        
        try {
            puestoServicio.save2(puesto);
            redirectAttributes.addFlashAttribute("success", "PuestoCT guardada con exito");  
        } catch (WebException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());        }
        return "redirect:/puesto/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String id) {
        puestoServicio.deleteById(id);
        return "redirect:/puesto/list";
    }
}
