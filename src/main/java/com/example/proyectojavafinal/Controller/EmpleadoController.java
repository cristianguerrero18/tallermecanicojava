package com.example.proyectojavafinal.Controller;

import com.example.proyectojavafinal.Entity.Empleado;
import com.example.proyectojavafinal.Entity.Rol;
import com.example.proyectojavafinal.Entity.Servicio;
import com.example.proyectojavafinal.Repository.EmpleadoRepository;
import com.example.proyectojavafinal.Service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @GetMapping("/empleado/login")
    public String login() {
        return "login3";
    }

    @PostMapping("/empleado/validar")
    public String validarUsuario(String correo, String contrasena, Model model) {
        // Buscar el empleado por correo y contraseña
        Empleado empleado = empleadoRepository.findByCorreoAndContrasena(correo, contrasena);

        // Verificar si el empleado existe
        if (empleado != null) {
            // Si el empleado existe, guardamos su correo en la sesión o donde lo necesites
            usuarioAutenticadoService.setCorreoAutenticado(correo);
            return "redirect:/empleado/menu";
        } else {
            // Si no se encuentra el empleado, mostramos el mensaje de error
            model.addAttribute("mensaje", "Credenciales incorrectas.");
            return "login3";
        }
    }

    @GetMapping("/empleado/menu")
    public String menu() {
        return "dasboardempleado";  // Corregir el nombre de la vista si es necesario
    }


    @GetMapping({"/verEmpleado", "/mostrarempleado", "/listarempleado"})
    public String listarEmpleado(Model model) {
        List<Empleado> listaEmpleado = empleadoRepository.findAll();
        model.addAttribute("listaEmpleado", listaEmpleado);
        return "verEmpleado";
    }

    @GetMapping("/verEmpleado/formEmpleado")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "formEmpleado";
    }

    @PostMapping("/guardarEmpleado")
    public String guardarServicio (Empleado empleado){
        empleadoRepository.save(empleado);
        return "redirect:/verEmpleado";
    }

    @GetMapping("/verEmpleado/editar/{cedula}")
    public String editarEmpleado(@PathVariable Long cedula, Model model) {
        Empleado empleado = empleadoRepository.findById(cedula).orElse(null);
        model.addAttribute("empleado", empleado);
        return "formEmpleado";
    }

    @GetMapping("/verEmpleado/eliminar/{cedula}")
    public String eliminarEmpleado(@PathVariable Long cedula) {
        empleadoRepository.deleteById(cedula);
        return "redirect:/verEmpleado";
    }
}
