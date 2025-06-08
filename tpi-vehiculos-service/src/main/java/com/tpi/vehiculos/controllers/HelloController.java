package com.tpi.vehiculos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hola desde VEHICULOS";
    }
}

