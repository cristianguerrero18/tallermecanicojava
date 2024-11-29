package com.example.proyectojavafinal.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="empleado")
public class Empleado {
    @Id
    @Column(name = "cedula", unique = true)
    private Long cedula;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    private String celular;

    @NotEmpty
    private String correo;

    @NotEmpty
    private String contrasena;

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public @NotEmpty String getNombre() {
        return nombre;
    }

    public void setNombre(@NotEmpty String nombre) {
        this.nombre = nombre;
    }

    public @NotEmpty String getApellido() {
        return apellido;
    }

    public void setApellido(@NotEmpty String apellido) {
        this.apellido = apellido;
    }

    public @NotEmpty String getCelular() {
        return celular;
    }

    public void setCelular(@NotEmpty String celular) {
        this.celular = celular;
    }

    public @NotEmpty String getCorreo() {
        return correo;
    }

    public void setCorreo(@NotEmpty String correo) {
        this.correo = correo;
    }

    public @NotEmpty String getContrasena() {
        return contrasena;
    }

    public void setContrasena(@NotEmpty String contrasena) {
        this.contrasena = contrasena;
    }
}
