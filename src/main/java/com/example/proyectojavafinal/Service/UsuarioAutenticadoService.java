package com.example.proyectojavafinal.Service;

import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {
    private String correoAutenticado;

    public String getCorreoAutenticado() {
        return correoAutenticado;
    }

    public void setCorreoAutenticado(String correo) {
        this.correoAutenticado = correo;
    }

    public void limpiar() {
        this.correoAutenticado = null;
    }
}
