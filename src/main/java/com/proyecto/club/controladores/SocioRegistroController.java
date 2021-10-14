
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.servicios.SocioService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author S
 */
@Controller
@RequestMapping("/socio")
public class SocioRegistroController {

    @Autowired
    private SocioService socioService;

    @GetMapping("/registro")
    public String registroSocio(Model model, @RequestParam(required = false) String id) {
        System.out.println(id);
        if (id != null) {
            Optional<Socio> optional = socioService.findById(id);

            if (optional.isPresent()) {
                Socio socio = optional.get();

                model.addAttribute("socio", socio);
            } else {
                return "redirect:/socio/list";
            }
        } else {
            model.addAttribute("socio", new Socio());
        }

        return "socio-registro.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_SOCIO')")
    @GetMapping("/carnet")
    public String carnet(Model model, @RequestParam(required = true) String id) {
        if (id != null) {
            Optional<Socio> optional = socioService.findById(id);
                Socio socio = optional.get();
                model.addAttribute("socio", socio);
            return "perfil-socio.html";
        }else{
            return "redirect:/";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String lista(Model model, @RequestParam(required = false) String query) {
        if (query != null) {
            model.addAttribute("socio", socioService.listAllByQ(query));
        } else {
            model.addAttribute("socio", socioService.listAll());
        }
        return "/html-administracion/socio/socio-list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            socioService.deleteById(id);
            model.addAttribute("socio", socioService.listAll());
            return "redirect:/socio/list";
        } catch (WebException ex) {
            ex.getMessage();
        }
        return "redirect:/socio/list";
    }

    // @GetMapping("/eliminar")
    // public String eliminar(@ModelAttribute Socio socio, Model model) throws
    // Exception{
    // try {
    // socioService.deleteByObject(socio);
    // model.addAttribute("socio", socioService.listAll());
    // return "redirect:/socio/list";
    //
    // } catch(Exception ex){
    // Logger.getLogger(SocioRegistroController.class.getName()).log(Level.SEVERE,
    // null, ex);
    // }
    // return "redirect:/socio/list";
    // }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/socioSeleccionado")
    public String mostrarSocio(String id, Model model) {
        if (id != null) {
            model.addAttribute("socio", socioService.findById(id));
        }
        return "socio-seleccionado";
    }

}
