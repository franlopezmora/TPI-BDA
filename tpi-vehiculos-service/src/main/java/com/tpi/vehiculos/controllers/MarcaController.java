package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.entities.Marca;
import com.tpi.vehiculos.services.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listar() {
        return ResponseEntity.ok(marcaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtenerPorId(@PathVariable Long id) {
        return marcaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Marca> crear(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.crear(marca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizar(@PathVariable Long id, @RequestBody Marca marca) {
        return marcaService.actualizar(id, marca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return marcaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public List<Marca> buscarPorNombre(@RequestParam String nombre) {
        return marcaService.buscarPorNombre(nombre);
    }

    @GetMapping("/buscar-parcial")
    public List<Marca> buscarPorNombreParcial(@RequestParam String nombre) {
        return marcaService.buscarPorNombreParcial(nombre);
    }

}