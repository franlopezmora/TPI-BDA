package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.dtos.MarcaDTO;
import com.tpi.vehiculos.dtos.VehiculoDTO;
import com.tpi.vehiculos.entities.Marca;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.services.VehiculoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<VehiculoDTO>> listar() {
        List<VehiculoDTO> dtoList = vehiculoService.listar().stream()
                .map(vehiculoService::toDTO)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehiculoDTO> obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerPorId(id)
                .map(vehiculoService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/all")
    public VehiculoDTO obtenerInclusoInactivo(@PathVariable Long id) {
        VehiculoDTO v = vehiculoService.obtenerInclusoInactivo(id);
        return v;
    }

    @PostMapping
    public ResponseEntity<VehiculoDTO> crear(@RequestBody VehiculoDTO dto) {
        Vehiculo creado = vehiculoService.crear(vehiculoService.fromDTO(dto));
        return ResponseEntity.ok(vehiculoService.toDTO(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehiculoDTO> actualizar(@PathVariable Long id, @RequestBody VehiculoDTO dto) {
        return vehiculoService.actualizar(id, vehiculoService.fromDTO(dto))
                .map(vehiculoService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (vehiculoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar-por-patente")
    public ResponseEntity<VehiculoDTO> buscarPorPatente(@RequestParam String patente) {
        return vehiculoService.buscarPorPatente(patente)
                .map(vehiculoService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar-por-anio")
    public ResponseEntity<List<VehiculoDTO>> buscarPorAnio(@RequestParam Integer anio) {
        List<VehiculoDTO> dtos = vehiculoService.buscarPorAnio(anio).stream()
                .map(vehiculoService::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/por-modelo/{idModelo}")
    public ResponseEntity<List<VehiculoDTO>> buscarPorModelo(@PathVariable Long idModelo) {
        List<VehiculoDTO> dtos = vehiculoService.buscarPorModeloId(idModelo).stream()
                .map(vehiculoService::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

}
