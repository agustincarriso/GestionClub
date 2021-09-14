/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Socio;
import com.proyecto.club.servicios.SocioService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author S
 */

@Controller
@RequestMapping("/socio")
public class SocioController {
    
    @Autowired
    public SocioService socioService;

    @GetMapping("/registro")
    public String registroSocio(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Socio> optional = socioService.findById(id);

            if (optional.isPresent()) {
                model.addAttribute("socio", optional.get());
            } else {
                return "redirect:/socio/list";
            }
        } else {
            model.addAttribute("socio",new Socio());
        }

        return "socio-registro.html";
    }

    @GetMapping("/list")
    public String lista(Model model) {
        model.addAttribute("socios", socioService.listAll());
        return "socio-list.html";
    }

    @PostMapping("/registrado")
    public String registrado(@ModelAttribute Socio socio, Model model, ModelMap modelo) throws Exception {
        try {
            System.out.println("Estoy en servicio aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            socioService.save(socio);

        } catch (Exception w) {
            
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            
            w.printStackTrace();
            w.getMessage();
            //modelo.put("error", w.getMessage());

            return "redirect:/socio/registro";
        }
        return "redirect:/socio/list";

    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            socioService.deleteById(id);
            model.addAttribute("socios", socioService.listAll());
            return "redirect:/socio/list";
        } catch (WebException ex) {
            Logger.getLogger(SocioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/socio/list";

    }


}
