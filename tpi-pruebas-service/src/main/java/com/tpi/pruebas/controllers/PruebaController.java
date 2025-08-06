package com.tpi.pruebas.controllers;

import com.tpi.pruebas.dtos.PosicionDTO;
import com.tpi.pruebas.dtos.PruebaDTO;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.exception.InteresadoRestringidoException;
import com.tpi.pruebas.services.PruebaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {
    private static final Logger log = LoggerFactory.getLogger(PruebaController.class);
    private final PruebaService pruebaService;

    public PruebaController(PruebaService pruebaService) {
        this.pruebaService = pruebaService;
    }

    @GetMapping
    public List<PruebaDTO> listar() {
        return pruebaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaDTO> obtenerPorId(@PathVariable Long id) {
        return pruebaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PruebaDTO pruebaDTO) {
        try {
            // 1) Declaro y lleno la entidad
            Prueba entidad = new Prueba();
            entidad.setFechaHoraInicio(pruebaDTO.getFechaHoraInicio());
            entidad.setFechaHoraFin(pruebaDTO.getFechaHoraFin());
            entidad.setComentarios(pruebaDTO.getComentario());
            entidad.setIdEmpleado(pruebaDTO.getEmpleado().getLegajo());
            entidad.setIdInteresado(pruebaDTO.getInteresado().getId());
            entidad.setIdVehiculo(pruebaDTO.getVehiculo().getId());

            PruebaDTO creada = pruebaService.crear(entidad);
            return ResponseEntity.ok(creada);
        } catch (IllegalArgumentException ex) {
            log.warn("Validación inválida al crear prueba: {}", ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            log.error("Error al crear prueba", ex);
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pruebaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody PruebaDTO pruebaDTO
    ) {
        // 1) Si no existe → 404
        if (!pruebaService.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Prueba con id=" + id + " no encontrada"
            );
        }

        // 2) Mapea el DTO a entidad
        Prueba entidad = new Prueba();
        entidad.setId(id);
        entidad.setFechaHoraInicio(pruebaDTO.getFechaHoraInicio());
        entidad.setFechaHoraFin(pruebaDTO.getFechaHoraFin());
        entidad.setComentarios(pruebaDTO.getComentario());
        entidad.setIdEmpleado(pruebaDTO.getEmpleado().getLegajo());
        entidad.setIdInteresado(pruebaDTO.getInteresado().getId());
        entidad.setIdVehiculo(pruebaDTO.getVehiculo().getId());

        try {
            // 3) Llamás al servicio; puede lanzar …
            PruebaDTO actualizado = pruebaService.actualizar(entidad);
            return ResponseEntity.ok(actualizado);

        } catch (InteresadoRestringidoException ex) {
            // 409 Conflict: existe el recurso pero su negocio impide la operación
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(ex.getMessage());

        } catch (IllegalArgumentException ex) {
            // 400 Bad Request: otras validaciones de negocio
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/validar-posicion")
    public ResponseEntity<Void> validarPosicion(@RequestBody PosicionDTO dto) {
        pruebaService.validarPosicion(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/activas")
    public List<PruebaDTO> listarPruebasActivas() {
        return pruebaService.listar().stream()
                .filter(p -> p.getFechaHoraFin() == null)
                .toList();
    }

    @GetMapping("/vehiculo-en-prueba/{idVehiculo}")
    public boolean vehiculoEnPrueba(@PathVariable Long idVehiculo) {
        log.info("ENTRY /pruebas/vehiculo-en-prueba/{} called", idVehiculo);
        boolean enPrueba = pruebaService.vehiculoEstaEnPrueba(idVehiculo);
        log.info("RESULT vehiculo-en-prueba/{} -> {}", idVehiculo, enPrueba);
        return enPrueba;
    }

}
