package com.example.proyectojavafinal.Entity;

import jakarta.persistence.* ;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="rol")
public class Rol {

    @Id
    @Column(name = "codigo", unique = true)
    private Long codigo  ;

    @NotEmpty
    private String descripcion ;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public @NotEmpty String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotEmpty String descripcion) {
        this.descripcion = descripcion;
    }
}
