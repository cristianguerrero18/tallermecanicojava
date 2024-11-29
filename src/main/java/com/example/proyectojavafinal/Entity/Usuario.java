package com.example.proyectojavafinal.Entity;
import jakarta.persistence.* ;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


@Entity
@Table(name="usuarios")
public class Usuario {

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

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehiculo> vehiculos;

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public @NotEmpty String getCelular() {
        return celular;
    }

    public void setCelular(@NotEmpty String celular) {
        this.celular = celular;
    }
}
