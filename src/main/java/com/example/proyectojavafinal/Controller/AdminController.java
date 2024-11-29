package com.example.proyectojavafinal.Controller;

import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/admin/login")
    public String login() {
        return "login";
    }

    @PostMapping("/admin/validar")
    public String validarUsuario(String correo, String contrasena, Model model) {
        Usuario usuario = usuarioRepository.findByCorreoAndContrasena(correo, contrasena);
        if (usuario != null && usuario.getRol().getDescripcion().equalsIgnoreCase("admin")) {
            return "redirect:/admin/menu";
        } else if (usuario != null) {
            model.addAttribute("mensaje", "Acceso restringido. Solo para administradores.");
        } else {
            model.addAttribute("mensaje", "Credenciales incorrectas.");
        }
        return "login";
    }

    @GetMapping("/admin/menu")
    public String menu() {
        return "dasboardadmin";
    }
}
