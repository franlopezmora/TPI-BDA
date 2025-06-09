package com.tpi.admin.controllers;

import com.tpi.admin.entities.Prueba;
import com.tpi.admin.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    @Autowired
    private PruebaRepository pruebaRepository;

    @GetMapping
    public List<Prueba> getAll() {
        return pruebaRepository.findAll();
    }

    @PostMapping
    public Prueba create(@RequestBody Prueba prueba) {
        return pruebaRepository.save(prueba);
    }
}