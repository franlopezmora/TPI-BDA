package com.tpi.vehiculos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehiculoController {

    @GetMapping("/vehiculos")
    public String listar() {
        return "Microservicio VEHICULOS funcionando ✅";
    }
}
