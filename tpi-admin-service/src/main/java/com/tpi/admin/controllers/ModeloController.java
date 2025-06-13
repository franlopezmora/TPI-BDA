package com.tpi.admin.controllers;

import com.tpi.admin.entities.Modelo;
import com.tpi.admin.services.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping
    public List<Modelo> obtenerTodos() {
        return modeloService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> obtenerPorId(@PathVariable Integer id) {
        return modeloService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Modelo> crear(@RequestBody Modelo modelo) {
        Modelo creado = modeloService.crear(modelo);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> actualizar(@PathVariable Integer id, @RequestBody Modelo modelo) {
        return modeloService.actualizar(id, modelo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (modeloService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
