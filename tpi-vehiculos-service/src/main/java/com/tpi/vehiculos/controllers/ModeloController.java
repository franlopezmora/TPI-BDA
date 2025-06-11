package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.dtos.ModeloDTO;
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
    public List<ModeloDTO> listar() {
        return modeloService.listarDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloDTO> obtenerPorId(@PathVariable Long id) {
        return modeloService.obtenerPorId(id)
                .map(modeloService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ModeloDTO> crear(@RequestBody ModeloDTO modeloDTO) {
        Modelo creado = modeloService.crear(modeloService.fromDTO(modeloDTO));
        return ResponseEntity.ok(modeloService.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloDTO> actualizar(@PathVariable Long id, @RequestBody ModeloDTO modeloDTO) {
        return modeloService.actualizar(id, modeloService.fromDTO(modeloDTO))
                .map(modeloService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return modeloService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-descripcion")
    public List<ModeloDTO> buscarPorDescripcion(@RequestParam String descripcion) {
        return modeloService.buscarPorDescripcion(descripcion).stream()
                .map(modeloService::toDTO)
                .toList();
    }

    @GetMapping("/por-marca/{idMarca}")
    public List<ModeloDTO> buscarPorMarca(@PathVariable Long idMarca) {
        return modeloService.buscarPorMarcaId(idMarca).stream()
                .map(modeloService::toDTO)
                .toList();
    }
}
