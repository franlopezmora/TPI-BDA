package com.tpi.pruebas.controllers;

import com.tpi.pruebas.dtos.PosicionDTO;
import com.tpi.pruebas.dtos.PruebaDTO;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.services.PruebaService;
import org.apache.coyote.Response;
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
    public ResponseEntity<?> crear(@RequestBody PruebaDTO pruebaDTO) {

        try{Prueba entidad = new Prueba();
        entidad.setFechaHoraInicio(pruebaDTO.getFechaHoraInicio());
        entidad.setFechaHoraFin(pruebaDTO.getFechaHoraFin());
        entidad.setComentarios(pruebaDTO.getComentario());
        entidad.setIdEmpleado(pruebaDTO.getEmpleado().getLegajo());
        entidad.setIdInteresado(pruebaDTO.getInteresado().getId());
        entidad.setIdVehiculo(pruebaDTO.getVehiculo().getId());
        PruebaDTO creada =  pruebaService.crear(entidad);
        return ResponseEntity.ok(creada);
        }
        catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
        catch(Exception ex){
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pruebaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
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
    public ResponseEntity<Boolean> vehiculoEnPrueba(@PathVariable Long idVehiculo) {
        boolean estaEnPrueba = pruebaService.vehiculoEstaEnPrueba(idVehiculo);
        return ResponseEntity.ok(estaEnPrueba);
    }

}
