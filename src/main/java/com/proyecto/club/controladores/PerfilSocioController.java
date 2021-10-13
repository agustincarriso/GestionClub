
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
public class PerfilSocioController {

	@GetMapping("/perfil-socio")
	public String perfilSocio() {
		return "perfil-socio.html";
	}
        	@GetMapping("/perfil-usuario")
	public String perfilUsuario() {
		return "perfil-usuario.html";
	}
}
