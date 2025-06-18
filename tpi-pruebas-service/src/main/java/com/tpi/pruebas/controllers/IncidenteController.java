package com.tpi.pruebas.controllers;

import com.tpi.pruebas.dtos.IncidenteDTO;
import com.tpi.pruebas.entities.Incidente;
import com.tpi.pruebas.repositories.IncidenteRepository;
import com.tpi.pruebas.services.PruebaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tpi.pruebas.dtos.RegistrarIncidenteDTO;

import java.util.List;

@RestController
@RequestMapping("/incidentes")
public class IncidenteController {

    private final IncidenteRepository incidenteRepository;
    private final PruebaService pruebaService;

    public IncidenteController(IncidenteRepository incidenteRepository, PruebaService pruebaService) {
        this.incidenteRepository = incidenteRepository;
        this.pruebaService = pruebaService;
    }

    @GetMapping
    public List<IncidenteDTO> getAll() {
        return pruebaService.listarIncidentes();
    }

    @PostMapping
    public ResponseEntity<String> registrarIncidente(@RequestBody RegistrarIncidenteDTO dto) {
        pruebaService.registrarIncidente(dto.getIdPrueba(), dto.getNombreTipoIncidente());
        return ResponseEntity.ok("Incidente registrado correctamente");
    }
}