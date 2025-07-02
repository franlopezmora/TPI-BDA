package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.dtos.PosicionDTO;
import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.services.PosicionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posiciones")
public class PosicionController {

    private final PosicionService posicionService;

    public PosicionController(PosicionService posicionService) {
        this.posicionService = posicionService;
    }

    @GetMapping
    public List<PosicionDTO> listar() {
        return posicionService.listarDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PosicionDTO> obtenerPorId(@PathVariable Long id) {
        return posicionService.obtenerPorId(id)
                .map(posicionService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PosicionDTO dto) {
        try {
            Posicion posicion = posicionService.fromDTO(dto, dto.getIdVehiculo());
            Posicion guardada = posicionService.crear(posicion);
            return ResponseEntity.ok(posicionService.toDTO(guardada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Devuelve el mensaje al cliente (Postman)
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PosicionDTO> actualizar(@PathVariable Long id, @RequestBody PosicionDTO dto) {
        Posicion posicion = posicionService.fromDTO(dto, dto.getIdVehiculo());
        return posicionService.actualizar(id, posicion)
                .map(posicionService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (posicionService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-vehiculo")
    public List<PosicionDTO> buscarPorVehiculo(@RequestParam Long idVehiculo) {
        return posicionService.buscarPorVehiculo(idVehiculo)
                .stream()
                .map(posicionService::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar-por-rango-fechas")
    public List<PosicionDTO> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin
    ) {
        return posicionService.buscarPorRangoFechas(inicio, fin)
                .stream()
                .map(posicionService::toDTO)
                .collect(Collectors.toList());
    }

}
