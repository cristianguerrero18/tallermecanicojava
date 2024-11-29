package com.example.proyectojavafinal.Repository;

import com.example.proyectojavafinal.Entity.Empleado;
import com.example.proyectojavafinal.Entity.OrdenServicio;
import com.example.proyectojavafinal.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio,Long> {
    List<OrdenServicio> findByUsuario(Usuario usuario);
    List<OrdenServicio> findByEmpleado(Empleado empleado);

}
