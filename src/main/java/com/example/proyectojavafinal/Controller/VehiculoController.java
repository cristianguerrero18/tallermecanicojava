package com.example.proyectojavafinal.Controller;

import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Entity.Vehiculo;
import com.example.proyectojavafinal.Repository.UsuarioRepository;
import com.example.proyectojavafinal.Repository.VehiculoRepository;
import com.example.proyectojavafinal.Service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @GetMapping("/vehiculos/nuevo")
    public String mostrarFormulario(Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);
        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el cliente autenticado.");
            return "error";
        }

        model.addAttribute("cliente", cliente); // Pasar el cliente al formulario
        model.addAttribute("vehiculo", new Vehiculo()); // Vehículo vacío para el formulario
        return "formVehiculo";
    }

    @PostMapping("/vehiculos/guardar")
    public String guardarVehiculo(Vehiculo vehiculo, Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: Cliente autenticado no válido.");
            return "formVehiculo";
        }

        vehiculo.setUsuario(cliente); // Asocia el vehículo con el cliente autenticado
        vehiculoRepository.save(vehiculo);
        return "redirect:/verVehiculo";
    }

    @GetMapping("/verVehiculo")
    public String verVehiculos(Model model) {
        List<Vehiculo> listaVehiculos = vehiculoRepository.findAll();
        model.addAttribute("listaVehiculos", listaVehiculos);
        return "verVehiculo";
    }
    @GetMapping("/vehiculo/eliminar/{placa}")
    public String eliminarVehiculo(@PathVariable("placa") String placa, Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el cliente autenticado.");
            return "error";
        }

        Vehiculo vehiculo = vehiculoRepository.findById(placa).orElse(null);

        if (vehiculo == null || !vehiculo.getUsuario().equals(cliente)) {
            model.addAttribute("mensajeError", "Error: Vehículo no encontrado o no pertenece al cliente autenticado.");
            return "error";
        }

        vehiculoRepository.delete(vehiculo);
        return "redirect:/verVehiculo";
    }

    @GetMapping("/vehiculo/editar/{placa}")
    public String mostrarFormularioEdicion(@PathVariable("placa") String placa, Vehiculo vehiculo, Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el cliente autenticado.");
            return "error";
        }


        vehiculo = vehiculoRepository.findById(placa).orElse(null);

        if (vehiculo == null || !vehiculo.getUsuario().equals(cliente)) {
            model.addAttribute("mensajeError", "Error: Vehículo no encontrado o no pertenece al cliente autenticado.");
            return "error";
        }

        model.addAttribute("cliente", cliente); // Pasar el cliente al formulario
        model.addAttribute("vehiculo", vehiculo); // Vehículo a editar
        return "formVehiculo"; // Usa el mismo formulario para edición
    }


}
