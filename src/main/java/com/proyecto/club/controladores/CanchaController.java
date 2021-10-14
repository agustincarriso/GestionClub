
package com.proyecto.club.controladores;

import com.proyecto.club.servicios.JugadorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

/**
 *
 * @author W7
 */
@Controller
@RequestMapping("")
public class CanchaController {

	@Autowired
	private JugadorServicio jugadorServicio;

	@GetMapping("/equipo")
    public String lista(Model model) {
            model.addAttribute("jugadores", jugadorServicio.listall());
        return "equipo";
    }
}