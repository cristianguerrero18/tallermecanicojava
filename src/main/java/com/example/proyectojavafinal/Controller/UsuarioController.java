package com.example.proyectojavafinal.Controller;


import com.example.proyectojavafinal.Entity.Rol;
import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Repository.RolRepository;
import com.example.proyectojavafinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List ;


@Controller
public class UsuarioController {

    @Autowired
    private RolRepository rolRepository ;

    @Autowired
    private UsuarioRepository usuarioRepository ;

    @GetMapping({"/verUsuario","mostrarUsuario","/listarUsuario"})
    public String listarUsuario(Model model ){
        List<Usuario> listaUsuario  = usuarioRepository.findAll() ;
        model.addAttribute("listarUsuario" , listaUsuario) ;
        return "verUsuario" ;
    }

    @GetMapping("/verUsuario/formUsuario")
    public String mostrarformulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        List<Rol> listaRol = rolRepository.findAll();
        model.addAttribute("listaRol", listaRol);
        return "formUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario (Usuario usuario){
        usuarioRepository.save(usuario);
        return "redirect:/verUsuario";
    }

    @GetMapping("/usuario/editar/{cedula}")
    public String modificarUsuario(@PathVariable("cedula") Long cedula, Model model) {
        Usuario usuario = usuarioRepository.findById(cedula).orElse(null);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaRol", rolRepository.findAll());
        return "formUsuario";
    }

    @GetMapping("/usuario/eliminar/{cedula}")
    public String eliminarUsuario(@PathVariable("cedula") Long cedula) {
        usuarioRepository.deleteById(cedula);
        return "redirect:/verUsuario";
    }

}