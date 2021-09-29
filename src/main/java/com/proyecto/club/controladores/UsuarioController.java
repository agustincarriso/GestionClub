
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Usuario;
import com.proyecto.club.servicios.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    public UsuarioService usuarioService;
    
    
    @GetMapping("/registro")
    public String registroUsuario(Model model, @RequestParam(required = false) String id) {
      
        System.out.println(id);
        if (id != null) {
            Optional<Usuario> optional = usuarioService.findById(id);

            if (optional.isPresent()){ 
                Usuario usuario = optional.get();
               
                model.addAttribute("usuario",usuario);
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
        model.addAttribute("usuario", usuarioService.listAll());
        return "usuario-list.html";
    }

    @PostMapping("/registrado")
    public String registrado(@ModelAttribute Usuario usuario, MultipartFile imagen, ModelMap modelo) throws Exception {
        try {  

            usuarioService.save(usuario, imagen);

        } catch (Exception w) {
            
            w.printStackTrace();
            
            modelo.put("error", w.getMessage());

            return "redirect:/usuario/registro";
        }
        return "redirect:/";

    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            usuarioService.deleteById(id);
            model.addAttribute("usuario", usuarioService.listAll());
            return "redirect:/usuario/list";
        } catch (WebException ex) {
           ex.getMessage();
        }
        return "redirect:/usuario/list";
    }
        
        
//        @GetMapping("/eliminar")
//        public String eliminar(@ModelAttribute Usuario usuario, Model model) throws Exception{
//        try {
//                usuarioService.deleteByObject(usuario);
//                model.addAttribute("usuario", usuarioService.listAll());
//                return "redirect:/usuario/list";
//                
//            } catch(Exception ex){
//                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return "redirect:/usuario/list";
//        }
            
        @GetMapping("/usuarioSeleccionado")
        public String mostrarUsuario
        (String id, Model model
        
            ){
        if (id != null) {

                model.addAttribute("usuario", usuarioService.findById(id));

            }
            return "usuario-seleccionado";

    
    }
    
}
