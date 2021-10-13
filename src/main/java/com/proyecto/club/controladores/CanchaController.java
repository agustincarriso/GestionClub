
package com.proyecto.club.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author W7
 */
@Controller
@RequestMapping("")
public class CanchaController {

	@GetMapping("/equipo")
	public String login() {
		return "equipo.html";
	}
}