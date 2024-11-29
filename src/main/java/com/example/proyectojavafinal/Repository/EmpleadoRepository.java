package com.example.proyectojavafinal.Repository;

import com.example.proyectojavafinal.Entity.Empleado;
import com.example.proyectojavafinal.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Empleado findByCorreoAndContrasena(String correo, String contrasena);
    Empleado findByCedula(Long cedula);
    Empleado findByCorreo(String correo);
}
