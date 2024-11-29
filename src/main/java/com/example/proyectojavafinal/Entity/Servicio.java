package com.example.proyectojavafinal.Entity;

import jakarta.persistence.* ;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="servicio")
public class Servicio {

    @Id
    @Column(name = "codigo", unique = true)
    private Long codigo ;

    @NotEmpty
    private String nombre ;

    @NotEmpty
    private String descripcion ;

    @NotNull
    private Long precio ;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public @NotEmpty String getNombre() {
        return nombre;
    }

    public void setNombre(@NotEmpty String nombre) {
        this.nombre = nombre;
    }

    public @NotEmpty String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotEmpty String descripcion) {
        this.descripcion = descripcion;
    }

    public @NotNull Long getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull Long precio) {
        this.precio = precio;
    }
}
