package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.dtos.MarcaDTO;
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
    public ResponseEntity<List<MarcaDTO>> listar() {
        return ResponseEntity.ok(marcaService.listarDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaDTO> obtenerPorId(@PathVariable Long id) {
        return marcaService.obtenerPorId(id)
                .map(marcaService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MarcaDTO> crear(@RequestBody MarcaDTO dto) {
        Marca nueva = marcaService.crear(marcaService.fromDTO(dto));
        return ResponseEntity.ok(marcaService.toDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MarcaDTO> actualizar(@PathVariable Long id, @RequestBody MarcaDTO dto) {
        return marcaService.actualizar(id, marcaService.fromDTO(dto))
                .map(marcaService::toDTO)
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
    public List<MarcaDTO> buscarPorNombre(@RequestParam String nombre) {
        return marcaService.buscarPorNombre(nombre).stream().map(marcaService::toDTO).toList();
    }

    @GetMapping("/buscar-parcial")
    public List<MarcaDTO> buscarPorNombreParcial(@RequestParam String nombre) {
        return marcaService.buscarPorNombreParcial(nombre).stream().map(marcaService::toDTO).toList();
    }

}