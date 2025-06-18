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
}
