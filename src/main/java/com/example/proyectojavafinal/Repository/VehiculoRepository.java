package com.example.proyectojavafinal.Repository;


import com.example.proyectojavafinal.Entity.Usuario;
import com.example.proyectojavafinal.Entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    List<Vehiculo> findByUsuario(Usuario usuario);
}
