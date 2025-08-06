package com.tpi.pruebas.controllers;

import com.tpi.pruebas.entities.TipoIncidente;
import com.tpi.pruebas.repositories.TipoIncidenteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-incidente")
public class TipoIncidenteController {

    private final TipoIncidenteRepository tipoIncidenteRepository;

    public TipoIncidenteController(TipoIncidenteRepository tipoIncidenteRepository) {
        this.tipoIncidenteRepository = tipoIncidenteRepository;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody TipoIncidente tipo) {
        TipoIncidente guardado = tipoIncidenteRepository.save(tipo);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public List<TipoIncidente> listar() {
        return tipoIncidenteRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoIncidente> actualizar(
            @PathVariable Long id,
            @RequestBody TipoIncidente tipoActualizado
    ) {
        return tipoIncidenteRepository.findById(id)
                .map(existing -> {
                    existing.setDescripcion(tipoActualizado.getDescripcion());
                    TipoIncidente saved = tipoIncidenteRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!tipoIncidenteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tipoIncidenteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
