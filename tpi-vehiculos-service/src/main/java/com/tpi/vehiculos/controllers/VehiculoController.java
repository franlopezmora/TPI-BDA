package com.tpi.vehiculos.controllers;

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
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(vehiculoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Vehiculo crear(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.crear(vehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        return vehiculoService.actualizar(id, vehiculo)
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
    public ResponseEntity<?> buscarPorPatente(@RequestParam String patente) {
        return vehiculoService.buscarPorPatente(patente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar-por-anio")
    public List<Vehiculo> buscarPorAnio(@RequestParam Integer anio) {
        return vehiculoService.buscarPorAnio(anio);
    }

    @GetMapping("/por-modelo/{idModelo}")
    public List<Vehiculo> buscarPorModelo(@PathVariable Long idModelo) {
        return vehiculoService.buscarPorModeloId(idModelo);
    }

}
