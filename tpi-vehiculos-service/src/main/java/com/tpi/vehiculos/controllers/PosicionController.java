package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.clients.PruebaClient;
import com.tpi.vehiculos.dtos.PosicionDTO;
import com.tpi.vehiculos.dtos.PruebaDTO;
import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.services.PosicionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posiciones")
public class PosicionController {
    private final PruebaClient pruebaClient;
    private final PosicionService posicionService;

    public PosicionController(PruebaClient pruebaClient, PosicionService posicionService) {
        this.pruebaClient = pruebaClient;
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
            // 1) Verifico que esté en prueba activa
            if (!pruebaClient.vehiculoEstaEnPrueba(dto.getIdVehiculo())) {
                return ResponseEntity
                        .badRequest()
                        .body("El vehículo no está en una prueba activa");
            }

            // 2) Traigo todas las pruebas activas y filtro por este vehículo
            List<PruebaDTO> activas = pruebaClient.listarPruebasActivas();
            Optional<PruebaDTO> pruebaOpt = activas.stream()
                    .filter(p -> p.getIdVehiculo().equals(dto.getIdVehiculo()))
                    .findFirst();

            if (pruebaOpt.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("No se encontró la prueba activa para este vehículo");
            }

            PruebaDTO prueba = pruebaOpt.get();
            LocalDateTime ahora = LocalDateTime.now();

            // 3) Impido posiciones anteriores al inicio de la prueba
            if (ahora.isBefore(prueba.getFechaHoraInicio())) {
                return ResponseEntity
                        .badRequest()
                        .body("No se puede registrar posición antes del inicio de la prueba");
            }

            // 4) Armo la entidad y fuerzo la fecha actual
            Posicion entidad = posicionService.fromDTO(dto, dto.getIdVehiculo());
            entidad.setFechaHora(ahora);

            // 5) Persisto y devuelvo
            Posicion guardada = posicionService.crear(entidad);
            return ResponseEntity.ok(posicionService.toDTO(guardada));

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(500)
                    .body("Error interno del servidor");
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
