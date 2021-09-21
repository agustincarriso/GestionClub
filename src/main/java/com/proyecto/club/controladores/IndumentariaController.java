package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Indumentaria;
import com.proyecto.club.servicios.IndumentariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author S
 */
@Controller
@RequestMapping("/indumentaria")
public class IndumentariaController {

    @Autowired
    private IndumentariaService indumentariaService;

    @GetMapping("/registro")
    public String registro() {

        return "indumentaria-registro";

    }

    @GetMapping("/list")
    public String listIndumentaria(Model model) {

        model.addAttribute("indumentaria", indumentariaService.listAll());

        return "indumentaria-list";
    }

    /*@PostMapping("/save")
    public String guardarIndumentaria(@RequestParam String nombre,@RequestParam String descripcion, @RequestParam Integer stock,@RequestParam Double precio,@RequestParam Talle talle,@RequestParam Color color){
    indumentariaService.save(nombre, descripcion, stock, precio, talle, color);
    
    return "redirect:/indumentaria";
    }*/
    @PostMapping("/registrado")
    public String guardarIndumentaria(@ModelAttribute("indumentaria") Indumentaria indumentaria) throws WebException {

        indumentariaService.save(indumentaria);
        return "redirect:/indumentaria/list";

    }

    @GetMapping("/registrado")
    public String registrado(){
    return "redirect:/";
    }
       
   @GetMapping("/delete")
   public String eliminarIndumentaria(@RequestParam(required = true) String id) throws WebException{
   indumentariaService.deleteById(id);
   return "redirect:/indumentaria/list";
}
    
}
