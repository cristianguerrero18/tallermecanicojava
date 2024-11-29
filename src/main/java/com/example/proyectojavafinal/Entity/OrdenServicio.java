package com.example.proyectojavafinal.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="orden_servicio")
public class OrdenServicio {

    @Id
    @Column(name = "codigo", unique = true)
    private Long codigo ;


    @ManyToOne
    @JoinColumn(name = "usuario_cedula", referencedColumnName = "cedula", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false) // Clave foránea a Usuario
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false) // Clave foránea a Usuario
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "empleado_id") // Asegúrate de que la columna corresponde a tu base de datos
    private Empleado empleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @NotEmpty
    private String estado = "Pendiente";

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFinalizacion;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public @NotEmpty String getEstado() {
        return estado;
    }

    public void setEstado(@NotEmpty String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }
}
