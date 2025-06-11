package com.tpi.pruebas.controllers;

import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.services.PruebaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

    private final PruebaService pruebaService;

    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @GetMapping
    public List<Prueba> listar() {
        return pruebaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> obtenerPorId(@PathVariable Long id) {
        return pruebaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prueba crear(@RequestBody Prueba prueba) {
        return pruebaService.crear(prueba);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pruebaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
