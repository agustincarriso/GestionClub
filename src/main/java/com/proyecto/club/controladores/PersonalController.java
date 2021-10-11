
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Personal;
import com.proyecto.club.servicios.PersonalService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/personal")
public class PersonalController {
    
    @Autowired
    public PersonalService personalService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/registro")
    public String registroPersonal(Model model, @RequestParam(required = false) String id) {
        
        if (id != null) {
            Optional<Personal> optional = personalService.findById(id);

            if (optional.isPresent()) {
                model.addAttribute("personal", optional.get());
            } else {
                return "redirect:/personal/list";
            }
        } else {
            model.addAttribute("personal", new Personal());
            
        }

        return "/html-administracion/personal/personal-registro";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String lista(Model model) {
        model.addAttribute("personal", personalService.listAll());
        return "/html-administracion/personal/personal-list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/registrado")

    public String registrado(@ModelAttribute Personal personal,MultipartFile imagen, ModelMap modelo) throws Exception {
        try {
            personalService.save(personal,imagen);


        } catch (Exception w) {
            
            modelo.put("error", w.getMessage());
            modelo.put("personal", personal);

            return "personal-registro";
        }
        return "redirect:/personal/list";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            personalService.deleteById(id);
            model.addAttribute("personal", personalService.listAll());
            return "redirect:/personal/list";
        } catch (WebException ex) {
            Logger.getLogger(PersonalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/personal/list";

}
    
    
    
}
