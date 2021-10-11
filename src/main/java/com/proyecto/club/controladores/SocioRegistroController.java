
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.servicios.SocioService;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author S
 */
@Controller
@RequestMapping("/socio")
public class SocioRegistroController {

    @Autowired
    public SocioService socioService;
    
    
    @GetMapping("/registro")
    public String registroSocio(Model model, @RequestParam(required = false) String id) {
      
        System.out.println(id);
        if (id != null) {
            Optional<Socio> optional = socioService.findById(id);

            if (optional.isPresent()){ 
                Socio socio = optional.get();
               
                model.addAttribute("socio",socio);
            } else {
                return "redirect:/socio/list";
            }
        } else {
            model.addAttribute("socio", new Socio());
        }

        return "socio-registro.html";

    }

    @GetMapping("/list")
    public String lista(Model model) {
        model.addAttribute("socio", socioService.listAll());
        return "/html-administracion/socio/socio-list.html";
    }

    @PostMapping("/registrado")
    public String registrado(Model model, @ModelAttribute Socio socio,RedirectAttributes redirectAttributes, @RequestParam(required = false) MultipartFile imagen, ModelMap modelo) throws Exception {
        try {  

            socioService.save(socio, imagen);

        } catch (Exception w) {
            model.addAttribute("error", w.getMessage());
            model.addAttribute("socio", socio);
            return "socio-registro.html";
        }
        return "redirect:/";

    }

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
        
        
//        @GetMapping("/eliminar")
//        public String eliminar(@ModelAttribute Socio socio, Model model) throws Exception{
//        try {
//                socioService.deleteByObject(socio);
//                model.addAttribute("socio", socioService.listAll());
//                return "redirect:/socio/list";
//                
//            } catch(Exception ex){
//                Logger.getLogger(SocioRegistroController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return "redirect:/socio/list";
//        }
            
        @GetMapping("/socioSeleccionado")
        public String mostrarSocio(String id, Model model){
        if (id != null) {
                model.addAttribute("socio", socioService.findById(id));
            }
        return "socio-seleccionado";
    }
    
}
