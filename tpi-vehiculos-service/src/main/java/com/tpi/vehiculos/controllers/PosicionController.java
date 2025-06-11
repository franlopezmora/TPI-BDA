package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.services.PosicionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posiciones")
public class PosicionController {

    private final PosicionService posicionService;

    public PosicionController(PosicionService posicionService) {
        this.posicionService = posicionService;
    }

    @GetMapping
    public List<Posicion> listar() {
        return posicionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Posicion> obtenerPorId(@PathVariable Long id) {
        return posicionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Posicion crear(@RequestBody Posicion posicion) {
        return posicionService.crear(posicion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Posicion> actualizar(@PathVariable Long id, @RequestBody Posicion posicion) {
        return posicionService.actualizar(id, posicion)
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
    public List<Posicion> buscarPorVehiculo(@RequestParam Long idVehiculo) {
        return posicionService.buscarPorVehiculo(idVehiculo);
    }

    @GetMapping("/buscar-por-rango-fechas")
    public List<Posicion> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin
    ) {
        return posicionService.buscarPorRangoFechas(inicio, fin);
    }

}
