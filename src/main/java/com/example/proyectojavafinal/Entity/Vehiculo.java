package com.example.proyectojavafinal.Entity;
import jakarta.persistence.* ;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="vehiculo")
public class Vehiculo {

    @Id
    @Column(name = "placa", unique = true)
    private String placa ;

    @NotEmpty
    private String marca ;

    @NotEmpty
    private String modelo ;

    @NotNull
    private Long anio ;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Clave foránea a Usuario
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public @NotEmpty String getMarca() {
        return marca;
    }

    public void setMarca(@NotEmpty String marca) {
        this.marca = marca;
    }

    public @NotEmpty String getModelo() {
        return modelo;
    }

    public void setModelo(@NotEmpty String modelo) {
        this.modelo = modelo;
    }

    public @NotNull Long getAnio() {
        return anio;
    }

    public void setAnio(@NotNull Long anio) {
        this.anio = anio;
    }
}
