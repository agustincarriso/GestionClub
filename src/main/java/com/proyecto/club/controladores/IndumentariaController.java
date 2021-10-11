package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;

import com.proyecto.club.entidades.Indumentaria;
import com.proyecto.club.servicios.IndumentariaService;
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

/**
 * @author S
 */
@Controller
@RequestMapping("/indumentaria")
public class IndumentariaController {
@Autowired
   public IndumentariaService indumentariaService;
    
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/registro")
    public String registroIndumentaria(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Indumentaria> optional = indumentariaService.findById(id);

            if (optional.isPresent()) {
                model.addAttribute("indumentaria", optional.get());
            } else {
                return "redirect:/indumentaria/list";
            }
        } else {
            model.addAttribute("indumentaria", new Indumentaria());
        }
        return "/html-administracion/indumentaria/indumentaria-registro.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @GetMapping("/list")
   public String lista(Model model){
   model.addAttribute("indumentaria", indumentariaService.listAll());
   return "/html-administracion/indumentaria/indumentaria-list.html";
   }
   
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @PostMapping("/registrado")

   public String registrado(@ModelAttribute Indumentaria indumentaria, MultipartFile imagen, ModelMap modelo) throws Exception{
   try{
        indumentariaService.save(indumentaria, imagen);
       
   }catch(Exception w){
       
       modelo.put("error", w.getMessage());
         
       return "redirect:/indumentaria/registro";
   }
       return "redirect:/indumentaria/list";
  
   }
   
   @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
   @GetMapping("/eliminar")
   public String eliminar(@RequestParam(required = true) String id, Model model){
       try{
            indumentariaService.deleteById(id);
            model.addAttribute("indumentaria", indumentariaService.listAll());    
            return "indumentaria-list.html";   
       } catch (WebException ex) {
           Logger.getLogger(IndumentariaController.class.getName()).log(Level.SEVERE, null, ex);
       }
          return "redirect:/indumentaria/list";
  
   
}
    
}
