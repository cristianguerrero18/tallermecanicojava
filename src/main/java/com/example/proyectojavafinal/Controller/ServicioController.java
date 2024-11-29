package com.example.proyectojavafinal.Controller;


import com.example.proyectojavafinal.Entity.Servicio;
import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping({"/verServicio", "/mostrarservicio", "/listarservicio"})
    public String listarServicio(Model model) {
        List<Servicio> listaServicio = servicioRepository.findAll();
        model.addAttribute("listaServicio", listaServicio);
        return "VerServicio";
    }

    @GetMapping("/verServicio/formServicio")
    public String mostrarFormulario(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "formServicio";
    }

    @PostMapping("/guardarServicio")
    public String guardarServicio (Servicio servicio){
        servicioRepository.save(servicio);
        return "redirect:/verServicio";
    }

    @GetMapping("/verServicio/editar/{codigo}")
    public String editarServicio(@PathVariable Long codigo, Model model) {
        Servicio servicio = servicioRepository.findById(codigo).orElse(null);
        model.addAttribute("servicio", servicio);
        return "formServicio";
    }

    @GetMapping("/verServicio/eliminar/{codigo}")
    public String eliminarServicio(@PathVariable Long codigo) {
        servicioRepository.deleteById(codigo);
        return "redirect:/verServicio";
    }

}
