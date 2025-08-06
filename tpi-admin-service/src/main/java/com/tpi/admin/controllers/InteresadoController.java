package com.tpi.admin.controllers;

import com.tpi.admin.clients.PruebaClient;
import com.tpi.admin.dtos.InteresadoDTO;
import com.tpi.admin.dtos.PruebaDTO;
import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import com.tpi.admin.services.InteresadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/interesados")
public class InteresadoController {

    private final InteresadoService interesadoService;
    private final PruebaClient pruebaClient;

    public InteresadoController(InteresadoService interesadoService,
                                PruebaClient pruebaClient) {
        this.interesadoService = interesadoService;
        this.pruebaClient      = pruebaClient;
    }

    @GetMapping
    public List<InteresadoDTO> getAll() {
        return interesadoService.listarInteresados();
    }

    @PostMapping
    public ResponseEntity<InteresadoDTO> create(
            @Valid @RequestBody InteresadoDTO dto
    ) {
        InteresadoDTO creado = interesadoService.crearInteresado(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteresadoDTO> getById(@PathVariable Long id) {
        return interesadoService.obtenerInteresadoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InteresadoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InteresadoDTO dto
    ) {
        try {
            InteresadoDTO actualizado = interesadoService.actualizarInteresado(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            interesadoService.eliminarInteresado(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/restringir/{id}")
    public ResponseEntity<Void> restringir(@PathVariable Long id) {
        try {
            interesadoService.restringirInteresado(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/restringidos")
    public List<InteresadoDTO> getRestringidos() {
        return interesadoService.listarRestringidos();
    }

    @GetMapping("/{id}/pruebas")
    public List<PruebaDTO> obtenerPruebas(@PathVariable Long id) {
        return pruebaClient.obtenerPruebasPorEmpleado(id);
    }
}

