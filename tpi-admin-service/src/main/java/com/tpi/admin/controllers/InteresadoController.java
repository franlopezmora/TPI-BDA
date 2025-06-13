package com.tpi.admin.controllers;

import com.tpi.admin.entities.Interesado;
import com.tpi.admin.services.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interesados")
public class InteresadoController {

    @Autowired
    private InteresadoService interesadoService;

    @GetMapping
    public List<Interesado> getAll() {
        return interesadoService.listarInteresados();
    }

    @PostMapping
    public Interesado create(@RequestBody Interesado interesado) {
        return interesadoService.crearInteresado(interesado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interesado> getById(@PathVariable Long id) {
        return interesadoService.obtenerInteresadoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interesado> update(@PathVariable Long id, @RequestBody Interesado interesadoActualizado) {
        try {
            Interesado actualizado = interesadoService.actualizarInteresado(id, interesadoActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        interesadoService.eliminarInteresado(id);
        return ResponseEntity.noContent().build();
    }
}
