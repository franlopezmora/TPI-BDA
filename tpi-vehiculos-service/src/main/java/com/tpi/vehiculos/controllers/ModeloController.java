package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.services.ModeloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modelos")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping
    public List<Modelo> listar() {
        return modeloService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> obtenerPorId(@PathVariable Long id) {
        return modeloService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Modelo crear(@RequestBody Modelo modelo) {
        return modeloService.crear(modelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> actualizar(@PathVariable Long id, @RequestBody Modelo modelo) {
        return modeloService.actualizar(id, modelo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (modeloService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-descripcion")
    public List<Modelo> buscarPorDescripcion(@RequestParam String descripcion) {
        return modeloService.buscarPorDescripcion(descripcion);
    }

    @GetMapping("/buscar-por-marca")
    public List<Modelo> buscarPorMarca(@RequestParam Long idMarca) {
        return modeloService.buscarPorMarcaId(idMarca);
    }

    @GetMapping("/buscar-descripcion")
    public List<Modelo> buscarPorDescripcionParcial(@RequestParam String descripcion) {
        return modeloService.buscarPorDescripcionParcial(descripcion);
    }

}
