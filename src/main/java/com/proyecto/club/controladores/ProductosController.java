
package com.proyecto.club.controladores;

import com.proyecto.club.excepciones.WebException;
import com.proyecto.club.entidades.Producto;
import com.proyecto.club.servicios.ProductoService;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    public ProductoService productoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/registro")
    public String registroProducto(Model model, @RequestParam(required = false) String id) {
        if (id != null) {
            Optional<Producto> optional = productoService.findById(id);

            if (optional.isPresent()) {
                model.addAttribute("productos", optional.get());
            } else {
                return "redirect:/productos/list";
            }
        } else {
            model.addAttribute("productos", new Producto());
        }

        return "/html-administracion/productos/productos-registro.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public String lista(Model model) {
        model.addAttribute("productos", productoService.listAll());
        return "/html-administracion/productos/productos-list.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/registrado")
    public String registrado(@ModelAttribute Producto producto, MultipartFile imagen, ModelMap modelo)
            throws Exception {
        try {
            productoService.save(producto, imagen);

        } catch (Exception w) {

            modelo.put("error", w.getMessage());

            return "redirect:/productos/registro";
        }
        return "redirect:/productos/list";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam(required = true) String id, Model model) {
        try {
            productoService.deleteById(id);
            model.addAttribute("productos", productoService.listAll());
            return "redirect:/productos/list";
        } catch (WebException ex) {
            Logger.getLogger(ProductosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/productos/list";

    }

}
