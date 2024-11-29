package com.example.proyectojavafinal.Controller;

import com.example.proyectojavafinal.Entity.Rol;
import com.example.proyectojavafinal.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping({"/verRol", "/mostrarrol", "/listarrol"})
    public String listarRol(Model model) {
        List<Rol> listaRol = rolRepository.findAll();
        model.addAttribute("listaRol", listaRol);
        return "VerRol";
    }

    @GetMapping("/verRol/formRol")
    public String mostrarFormulario(Model model) {
        model.addAttribute("rol", new Rol());
        return "formRol";
    }

    @PostMapping("/guardarRol")
    public String guardarRol(Rol rol) {
        if (rol.getCodigo() != null) { // Si el código no es null, busca el Rol existente.
            Rol rolExistente = rolRepository.findById(rol.getCodigo()).orElse(null);
            if (rolExistente != null) {
                rolExistente.setDescripcion(rol.getDescripcion());
                rolRepository.save(rolExistente);
            } else {

                rolRepository.save(rol);
            }
        } else {

            rolRepository.save(rol);
        }
        return "redirect:/verRol";
    }

    @GetMapping("/verRol/editar/{codigo}")
    public String editarRol(@PathVariable Long codigo, Model model) {
        Rol rol = rolRepository.findById(codigo).orElse(null);
        model.addAttribute("rol", rol);
        return "formRol";
    }

    @GetMapping("/verRol/eliminar/{codigo}")
    public String eliminarRol(@PathVariable Long codigo) {
        rolRepository.deleteById(codigo);
        return "redirect:/verRol";
    }
}
