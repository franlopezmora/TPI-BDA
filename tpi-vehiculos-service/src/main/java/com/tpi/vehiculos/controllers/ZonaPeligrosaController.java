package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.entities.ZonaPeligrosa;
import com.tpi.vehiculos.services.ZonaPeligrosaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zonas-peligrosas")
public class ZonaPeligrosaController {

    private final ZonaPeligrosaService zonaPeligrosaService;

    public ZonaPeligrosaController(ZonaPeligrosaService zonaPeligrosaService) {
        this.zonaPeligrosaService = zonaPeligrosaService;
    }

    @GetMapping
    public List<ZonaPeligrosa> listar() {
        return zonaPeligrosaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaPeligrosa> obtenerPorId(@PathVariable Long id) {
        return zonaPeligrosaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ZonaPeligrosa crear(@RequestBody ZonaPeligrosa zona) {
        return zonaPeligrosaService.crear(zona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaPeligrosa> actualizar(@PathVariable Long id, @RequestBody ZonaPeligrosa zona) {
        return zonaPeligrosaService.actualizar(id, zona)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (zonaPeligrosaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public List<ZonaPeligrosa> buscarPorNombre(@RequestParam String nombre) {
        return zonaPeligrosaService.buscarPorNombreExacto(nombre);
    }

    @GetMapping("/buscar-parcial")
    public List<ZonaPeligrosa> buscarPorNombreParcial(@RequestParam String nombre) {
        return zonaPeligrosaService.buscarPorNombreParcial(nombre);
    }

    @GetMapping("/contiene")
    public List<ZonaPeligrosa> buscarZonasQueContienen(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return zonaPeligrosaService.buscarZonasQueContienen(lat, lon);
    }
}
