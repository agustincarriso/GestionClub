
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
public class RegistroExitosoController {

	@GetMapping("/registro-exitoso")
	public String registroExitoso() {
		return "registro-exitoso.html";
	}
	
	@GetMapping("/bienvenidos")
	public String bienvenidos() {
		return "bienvenidos.html";
	}
	
//	@GetMapping("/asociado-exitoso")
//	public String bienvenidoSocio() {
//		return "asociado-exitoso.html";
//	}
	
	
}
