package com.tpi.pruebas.controllers;

import com.tpi.pruebas.dtos.PruebaDTO;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.services.PruebaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pruebas")
public class PruebaController {

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
    public PruebaDTO crear(@RequestBody PruebaDTO pruebaDTO) {

        Prueba entidad = new Prueba();
        entidad.setFechaHoraInicio(pruebaDTO.getFechaHoraInicio());
        entidad.setFechaHoraFin(pruebaDTO.getFechaHoraFin());
        entidad.setComentarios(pruebaDTO.getComentario());
        entidad.setIdEmpleado(pruebaDTO.getEmplado().getLegajo());
        entidad.setIdInteresado(pruebaDTO.getInteresado().getId());
        entidad.setIdVehiculo(pruebaDTO.getVehiculo().getId());
        return pruebaService.crear(entidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pruebaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
