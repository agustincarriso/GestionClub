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
                model.addAttribute("socio",optional.get());
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
        model.addAttribute("socio", socioService.listAll());
        return "socio-list.html";
    }

    @PostMapping("/registrado")
    public String registrado(@ModelAttribute Socio socio, MultipartFile imagen, ModelMap modelo) throws Exception {
        try {

            socioService.save(socio,imagen);

        } catch (Exception w) {

            w.getMessage();

            //modelo.put("error", w.getMessage());
            return "socio-registro";
        }
        return "redirect:/socio/list";

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


}
