package com.example.proyectojavafinal.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class homeController {

    @GetMapping("/")
    public String index() {
        // Esta es la página principal (index.html)
        return "index"; // Retorna el archivo "index.html"
    }

    @GetMapping("/home")
    public String home() {
        // Esta es la página home.html
        return "home"; // Retorna el archivo "home.html"
    }
}