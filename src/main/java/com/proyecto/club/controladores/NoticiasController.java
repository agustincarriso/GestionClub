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
public class NoticiasController {

	@GetMapping("/noticias")
	public String noticia() {
		return "noticias.html";
	}

	@GetMapping("/html-administracion/noticias-web/noticia-1.html")
	public String noticia1() {
		return "/html-administracion/noticias-web/noticia-1.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-2.html")
	public String noticia2() {
		return "/html-administracion/noticias-web/noticia-2.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-3.html")
	public String noticia3() {
		return "/html-administracion/noticias-web/noticia-3.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-4.html")
	public String noticia4() {
		return "/html-administracion/noticias-web/noticia-4.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-5.html")
	public String noticia5() {
		return "/html-administracion/noticias-web/noticia-5.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-6.html")
	public String noticia6() {
		return "/html-administracion/noticias-web/noticia-6.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-7.html")
	public String noticia7() {
		return "/html-administracion/noticias-web/noticia-7.html";
	}
	
	@GetMapping("/html-administracion/noticias-web/noticia-8.html")
	public String noticia8() {
		return "/html-administracion/noticias-web/noticia-8.html";
	}
	
	
}
