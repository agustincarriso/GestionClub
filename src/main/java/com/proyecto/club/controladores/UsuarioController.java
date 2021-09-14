
package com.proyecto.club.Controladores;

import com.proyecto.club.Excepciones.WebException;
import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.servicios.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;

    @GetMapping("/registro")
    public String registroUsuario(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Usuario> optional = usuarioService.findById(id);

            if (optional.isPresent()) {
                model.addAttribute("usuario", optional.get());
            } else {
                return "redirect:/usuario/list";
            }
        } else {
            model.addAttribute("usuario", new Usuario());
        }

        return "usuario-registro.html";
    }

    @GetMapping("/list")
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioService.listAll());
        return "usuario-list.html";
    }

    @PostMapping("/registrado")
    public String registrado(@ModelAttribute Usuario usuario, Model model, ModelMap modelo) throws Exception {
        try {
            usuarioService.save(usuario);

        } catch (Exception w) {

            modelo.put("error", w.getMessage());

            return "redirect:/usuario/registro";
        }
        return "redirect:/usuario/list";

    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            usuarioService.deleteById(id);
            model.addAttribute("usuarios", usuarioService.listAll());
            return "redirect:/usuario/list";
        } catch (WebException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/usuario/list";

}
    
    
}
