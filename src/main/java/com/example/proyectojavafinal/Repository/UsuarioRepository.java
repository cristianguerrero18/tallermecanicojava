package com.example.proyectojavafinal.Repository;

import com.example.proyectojavafinal.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    Usuario findByCorreoAndContrasena(String correo, String contrasena);
    Usuario findByCorreo(String correo);
}
