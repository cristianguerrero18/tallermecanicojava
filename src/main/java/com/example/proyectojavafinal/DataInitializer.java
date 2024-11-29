package com.example.proyectojavafinal;

import com.example.proyectojavafinal.Entity.Empleado;
import com.example.proyectojavafinal.Entity.Rol;
import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Repository.EmpleadoRepository;
import com.example.proyectojavafinal.Repository.RolRepository;
import com.example.proyectojavafinal.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        // Verifica si el rol 'admin' ya existe
        if (!rolRepository.existsById(1L)) {
            // Crea el rol admin si no existe
            Rol rolAdmin = new Rol();
            rolAdmin.setCodigo(1L);
            rolAdmin.setDescripcion("admin");
            rolRepository.save(rolAdmin);
            System.out.println("Rol admin creado con éxito.");
        } else {
            System.out.println("El rol admin ya existe.");
        }

        // Verifica si el rol 'cliente' ya existe
        if (!rolRepository.existsById(2L)) {
            // Crea el rol cliente si no existe
            Rol rolCliente = new Rol();
            rolCliente.setCodigo(2L);
            rolCliente.setDescripcion("cliente");
            rolRepository.save(rolCliente);
            System.out.println("Rol cliente creado con éxito.");
        } else {
            System.out.println("El rol cliente ya existe.");
        }

        // Verifica si el usuario admin ya existe
        if (!usuarioRepository.existsById(1230129312L)) {
            // Crea un usuario admin si no existe
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setCedula(1230129312L);
            usuarioAdmin.setNombre("Cristian");
            usuarioAdmin.setApellido("Guerrero");
            usuarioAdmin.setCorreo("admin@gmail.com");
            usuarioAdmin.setContrasena("admin123");
            usuarioAdmin.setCelular("1234567890");

            // Asocia el rol admin con el usuario
            Rol rolAdmin = rolRepository.findById(1L).orElse(null);
            if (rolAdmin != null) {
                usuarioAdmin.setRol(rolAdmin);
                usuarioRepository.save(usuarioAdmin);
                System.out.println("Usuario admin creado con éxito.");
            }
        } else {
            System.out.println("El usuario admin ya existe.");
        }

        // Verifica si el empleado ya existe
        if (!empleadoRepository.existsById(1122334455L)) { // Aquí va la cédula del empleado
            // Crea un nuevo empleado
            Empleado empleado = new Empleado();
            empleado.setCedula(1122334455L);  // Cédula del empleado
            empleado.setNombre("Luis");
            empleado.setApellido("Martínez");
            empleado.setCorreo("empleado@gmail.com");
            empleado.setContrasena("empleado123");
            empleado.setCelular("0981234567");

            // Guarda el empleado en la base de datos
            empleadoRepository.save(empleado);
            System.out.println("Empleado creado con éxito.");
        } else {
            System.out.println("El empleado ya existe.");
        }

        // Verifica si el usuario cliente ya existe
        if (!usuarioRepository.existsById(1234567890L)) {
            // Crea un usuario cliente si no existe
            Usuario usuarioCliente = new Usuario();
            usuarioCliente.setCedula(1234567890L);
            usuarioCliente.setNombre("Ana");
            usuarioCliente.setApellido("Pérez");
            usuarioCliente.setCorreo("cliente@gmail.com");
            usuarioCliente.setContrasena("cliente123");
            usuarioCliente.setCelular("0987654321");

            // Asocia el rol cliente con el usuario
            Rol rolCliente = rolRepository.findById(2L).orElse(null);
            if (rolCliente != null) {
                usuarioCliente.setRol(rolCliente);
                usuarioRepository.save(usuarioCliente);
                System.out.println("Usuario cliente creado con éxito.");
            }
        } else {
            System.out.println("El usuario cliente ya existe.");
        }
    }
}
