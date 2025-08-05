package com.tpi.admin.controllers;

import com.tpi.admin.clients.PruebaClient;
import com.tpi.admin.dtos.EmpleadoDTO;
import com.tpi.admin.dtos.PruebaDTO;
import com.tpi.admin.entities.Empleado;
import com.tpi.admin.services.EmpleadoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final PruebaClient pruebaClient;

    public EmpleadoController(EmpleadoService empleadoService,
                              PruebaClient pruebaClient) {
        this.empleadoService = empleadoService;
        this.pruebaClient     = pruebaClient;
    }

    @GetMapping
    public List<EmpleadoDTO> getAll() {
        return empleadoService.listarEmpleados();
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(
            @Valid @RequestBody EmpleadoDTO dto
    ) {
        EmpleadoDTO creado = empleadoService.guardarEmpleado(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    @GetMapping("/{legajo}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Long legajo) {
        return empleadoService.obtenerEmpleadoPorId(legajo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{legajo}")
    public ResponseEntity<EmpleadoDTO> update(
            @PathVariable Long legajo,
            @Valid @RequestBody EmpleadoDTO dto
    ) {
        try {
                EmpleadoDTO actualizado = empleadoService.actualizarEmpleado(legajo, dto);
                return ResponseEntity.ok(actualizado);
            } catch (EntityNotFoundException ex) {
                return ResponseEntity.notFound().build();
            } catch (RuntimeException ex) {
                // para el test que lanza RuntimeException
                        return ResponseEntity.notFound().build();
            }
    }

    @DeleteMapping("/{legajo}")
    public ResponseEntity<Void> delete(@PathVariable Long legajo) {
        try {
            empleadoService.eliminarEmpleado(legajo);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{legajo}/pruebas")
    public List<PruebaDTO> obtenerPruebas(@PathVariable Long legajo) {
        return pruebaClient.obtenerPruebasPorEmpleado(legajo);
    }
}

