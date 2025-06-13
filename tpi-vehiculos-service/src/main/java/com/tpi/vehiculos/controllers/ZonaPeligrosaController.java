package com.tpi.vehiculos.controllers;

import com.tpi.vehiculos.dtos.ZonaPeligrosaDTO;
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
    public List<ZonaPeligrosaDTO> listar() {
        return zonaPeligrosaService.listarDTO();
    }

    @PostMapping
    public ZonaPeligrosa crear(@RequestBody ZonaPeligrosaDTO dto) {
        return zonaPeligrosaService.crear(zonaPeligrosaService.fromDTO(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaPeligrosaDTO> obtenerPorId(@PathVariable Long id) {
        return zonaPeligrosaService.obtenerPorId(id)
                .map(zonaPeligrosaService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaPeligrosa> actualizar(@PathVariable Long id, @RequestBody ZonaPeligrosaDTO dto) {
        return zonaPeligrosaService.actualizar(id, zonaPeligrosaService.fromDTO(dto))
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
    public List<ZonaPeligrosaDTO> buscarPorNombre(@RequestParam String nombre) {
        return zonaPeligrosaService.buscarPorNombreDTO(nombre);
    }

    @GetMapping("/buscar-parcial")
    public List<ZonaPeligrosaDTO> buscarPorNombreParcial(@RequestParam String nombre) {
        return zonaPeligrosaService.buscarPorNombreParcialDTO(nombre);
    }

    @GetMapping("/contiene")
    public List<ZonaPeligrosaDTO> buscarZonasQueContienen(@RequestParam double lat, @RequestParam double lon) {
        return zonaPeligrosaService.buscarZonasQueContienenDTO(lat, lon);
    }
}
