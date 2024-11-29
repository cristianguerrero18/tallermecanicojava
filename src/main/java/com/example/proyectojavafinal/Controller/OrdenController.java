package com.example.proyectojavafinal.Controller;

import com.example.proyectojavafinal.Entity.Empleado;
import com.example.proyectojavafinal.Entity.OrdenServicio;
import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Repository.*;
import com.example.proyectojavafinal.Service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class OrdenController {

    @Autowired
    private OrdenServicioRepository ordenServicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Mostrar formulario para nueva orden
    @GetMapping("/ordenes/nueva")
    public String mostrarFormulario(Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);
        Usuario admin =  usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el cliente autenticado.");
            return "error";
        }

        // Filtrar los vehículos del cliente autenticado
        model.addAttribute("orden", new OrdenServicio());
        model.addAttribute("listaServicio", servicioRepository.findAll());
        model.addAttribute("listaVehiculo", vehiculoRepository.findByUsuario(cliente)); // Aquí filtramos por cliente

        return "formOrden";
    }

    // Guardar nueva orden
    @PostMapping("/ordenes/guardar")
    public String guardarOrden(@ModelAttribute OrdenServicio orden, Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: Cliente autenticado no válido.");
            return "formOrden";
        }

        orden.setUsuario(cliente); // Asignar el cliente autenticado
        orden.setEstado("Pendiente");
        orden.setFechaCreacion(java.time.LocalDateTime.now());
        orden.setFechaFinalizacion(null);
        orden.setEmpleado(null);

        ordenServicioRepository.save(orden);
        return "redirect:/ordenes";
    }

    // Listar las órdenes del cliente
    @GetMapping("/ordenes")
    public String listarOrdenes(Model model) {
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();
        Usuario cliente = usuarioRepository.findByCorreo(correoAutenticado);

        if (cliente == null) {
            model.addAttribute("mensajeError", "Error: Cliente autenticado no válido.");
            return "error";
        }

        model.addAttribute("ordenes", ordenServicioRepository.findByUsuario(cliente));
        return "VerOrden";
    }

    // Editar orden existente
    @GetMapping("/ordenes/editar/{codigo}")
    public String mostrarFormularioEdicion(@PathVariable("codigo") Long codigo, Model model) {
        Optional<OrdenServicio> ordenOpt = ordenServicioRepository.findById(codigo);
        if (ordenOpt.isPresent()) {
            model.addAttribute("orden", ordenOpt.get());
            model.addAttribute("listaServicio", servicioRepository.findAll());
            model.addAttribute("listaVehiculo", vehiculoRepository.findAll());
            return "formOrden";
        } else {
            return "redirect:/ordenes";
        }
    }

    // Eliminar una orden
    @GetMapping("/ordenes/eliminar/{codigo}")
    public String eliminarOrden(@PathVariable("codigo") Long codigo) {
        ordenServicioRepository.deleteById(codigo);
        return "redirect:/ordenes";
    }

    // Ver órdenes del administrador
    @GetMapping("/admin/ordenes")
    public String listarOrdenesAdmin(Model model) {
        model.addAttribute("ordenes", ordenServicioRepository.findAll());
        model.addAttribute("listaEmpleados", empleadoRepository.findAll());
        return "verOrdenAdmin";
    }

    // Asignar empleado a una orden
    @PostMapping("/admin/ordenes/asignar-empleado/{codigo}")
    public String asignarEmpleado(
            @PathVariable("codigo") Long codigo,
            @ModelAttribute("empleadoId") Long empleadoId) {
        Optional<OrdenServicio> ordenOpt = ordenServicioRepository.findById(codigo);
        Optional<Empleado> empleadoOpt = empleadoRepository.findById(empleadoId);

        if (ordenOpt.isPresent() && empleadoOpt.isPresent()) {
            OrdenServicio orden = ordenOpt.get();
            orden.setEmpleado(empleadoOpt.get());
            ordenServicioRepository.save(orden);
        }
        return "redirect:/admin/ordenes";
    }

    // Asignar fecha de finalización a una orden
    @PostMapping("/admin/ordenes/asignar-fecha/{codigo}")
    public String asignarFechaFinalizacion(
            @PathVariable("codigo") Long codigo,
            @ModelAttribute("fechaFinalizacion") String fechaFinalizacion) {
        Optional<OrdenServicio> ordenOpt = ordenServicioRepository.findById(codigo);

        if (ordenOpt.isPresent()) {
            OrdenServicio orden = ordenOpt.get();
            // Convertir el String a LocalDate
            LocalDate fecha = LocalDate.parse(fechaFinalizacion);
            orden.setFechaFinalizacion(fecha);  // Asignar el valor solo de la fecha
            orden.setEstado("En Curso");
            ordenServicioRepository.save(orden);
        }

        return "redirect:/admin/ordenes";
    }

    // Ver las órdenes del empleado autenticado
    @GetMapping("/empleado/ordenes")
    public String listarOrdenesEmpleado(Model model) {
        // Obtener el correo del empleado autenticado
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();

        // Buscar el empleado en la base de datos por correo
        Empleado empleado = empleadoRepository.findByCorreo(correoAutenticado);

        if (empleado == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el empleado autenticado.");
            return "error";
        }

        // Obtener las órdenes que están asignadas a este empleado
        model.addAttribute("ordenes", ordenServicioRepository.findByEmpleado(empleado));
        return "verOrdenEmpleado";
    }
    // Finalizar una orden
    @PostMapping("/empleado/ordenes/finalizar/{codigo}")
    public String finalizarOrden(@PathVariable("codigo") Long codigo, Model model) {
        // Obtener el correo del empleado autenticado
        String correoAutenticado = usuarioAutenticadoService.getCorreoAutenticado();

        // Buscar el empleado en la base de datos por correo
        Empleado empleado = empleadoRepository.findByCorreo(correoAutenticado);

        if (empleado == null) {
            model.addAttribute("mensajeError", "Error: No se encontró el empleado autenticado.");
            return "error";
        }

        // Buscar la orden por código
        Optional<OrdenServicio> ordenOpt = ordenServicioRepository.findById(codigo);
        if (ordenOpt.isPresent()) {
            OrdenServicio orden = ordenOpt.get();

            // Verificar si la orden está asignada al empleado
            if (orden.getEmpleado() != null && orden.getEmpleado().getCedula().equals(empleado.getCedula())) {
                // Cambiar el estado de la orden a "Finalizado"
                orden.setEstado("Finalizado");
                orden.setFechaFinalizacion(java.time.LocalDate.now()); // Asignar la fecha de finalización
                ordenServicioRepository.save(orden);
            } else {
                model.addAttribute("mensajeError", "Error: No tienes acceso a esta orden.");
                return "error";
            }
        } else {
            model.addAttribute("mensajeError", "Error: No se encontró la orden.");
            return "error";
        }

        return "redirect:/empleado/ordenes";
    }
    // Mostrar factura de una orden
    @GetMapping("/ordenes/factura/{codigo}")
    public String verFactura(@PathVariable("codigo") Long codigo, Model model) {
        Optional<OrdenServicio> ordenOpt = ordenServicioRepository.findById(codigo);
        if (ordenOpt.isPresent()) {
            OrdenServicio orden = ordenOpt.get();
            Long precioServicio = orden.getServicio().getPrecio();  // Obtener el precio del servicio
            model.addAttribute("orden", orden);
            model.addAttribute("precioServicio", precioServicio);
            return "verFactura";  // Vista para la factura
        } else {
            model.addAttribute("mensajeError", "Error: No se encontró la orden.");
            return "error";
        }
    }


}
