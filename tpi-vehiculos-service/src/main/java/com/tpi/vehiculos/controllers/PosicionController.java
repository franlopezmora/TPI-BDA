package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.clients.PruebaClient;
import com.tpi.vehiculos.dtos.PosicionDTO;
import com.tpi.vehiculos.dtos.PruebaDTO;
import com.tpi.vehiculos.dtos.VehiculoDTO;
import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.repositories.VehiculoRepository;
import com.tpi.vehiculos.services.PosicionService;
import com.tpi.vehiculos.services.VehiculoService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posiciones")
public class PosicionController {
    private final PruebaClient pruebaClient;
    private final PosicionService posicionService;
    private final VehiculoService vehiculoService;
    private final VehiculoRepository vehiculoRepository;
    private static final Logger log = LoggerFactory.getLogger(PosicionController.class);

    public PosicionController(PruebaClient pruebaClient, PosicionService posicionService, VehiculoService vehiculoService, VehiculoRepository vehiculoRepository) {
        this.pruebaClient = pruebaClient;
        this.posicionService = posicionService;
        this.vehiculoService = vehiculoService;
        this.vehiculoRepository = vehiculoRepository;
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
        log.info("Llega POST /posiciones con dto: {}", dto);

        // 0) Vehículo existe?
        Optional<Vehiculo> vehOpt = vehiculoRepository.findById(dto.getIdVehiculo());
        if (vehOpt.isEmpty()) {
            String msg = "Vehículo " + dto.getIdVehiculo() + " no encontrado";
            log.warn(msg);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", msg));
        }

        Vehiculo veh = vehOpt.get();
        // 1) Vehículo activo?
        Boolean activo = veh.getActivo();
        if (Boolean.FALSE.equals(activo)) {
            String msg = "Vehículo " + dto.getIdVehiculo() + " dado de baja";
            log.warn(msg);
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", msg));
        }

        // 2) Vehículo en prueba activa?
        boolean enPrueba;
        try {
            enPrueba = pruebaClient.vehiculoEstaEnPrueba(dto.getIdVehiculo());
        } catch (FeignException.NotFound nf) {
            log.info("Feign 404 → vehículo {} no está en prueba activa", dto.getIdVehiculo());
            enPrueba = false;
        }
        if (!enPrueba) {
            String msg = "Vehículo " + dto.getIdVehiculo() + " no está en prueba activa";
            log.warn(msg);
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", msg));
        }

        // 3) Resto de validaciones (fecha inicio, etc.) y guardado
        List<PruebaDTO> activas = pruebaClient.listarPruebasActivas();
        Optional<PruebaDTO> pruebaOpt = activas.stream()
                .filter(p -> p.getIdVehiculo().equals(dto.getIdVehiculo()))
                .findFirst();
        if (pruebaOpt.isEmpty()) {
            String msg = "No se encontró la prueba activa para este vehículo";
            log.warn(msg);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", msg));
        }
        PruebaDTO prueba = pruebaOpt.get();
        LocalDateTime ahora = LocalDateTime.now();
        if (ahora.isBefore(prueba.getFechaHoraInicio())) {
            String msg = "No se puede registrar posición antes del inicio de la prueba";
            log.warn(msg);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", msg));
        }

        Posicion entidad = posicionService.fromDTO(dto, dto.getIdVehiculo());
        entidad.setFechaHora(ahora);
        Posicion guardada = posicionService.crear(entidad);
        log.info("Posición guardada para vehículo {}", dto.getIdVehiculo());
        return ResponseEntity.ok(posicionService.toDTO(guardada));
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
