package com.tpi.reportes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportes")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hola desde REPORTES";
    }
}
