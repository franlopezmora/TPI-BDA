package com.tpi.admin.controllers;

import com.tpi.admin.clients.PruebaClient;
import com.tpi.admin.dtos.PruebaDTO;
import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import com.tpi.admin.services.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/interesados")
public class InteresadoController {

    @Autowired
    private InteresadoService interesadoService;
    @Autowired
    private InteresadoRepository interesadoRepository;
    @Autowired
    private PruebaClient pruebaClient;


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

    @PutMapping("/restringir/{id}")
    public void restringirInteresado(@PathVariable Long id) {
        Interesado interesado = interesadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Interesado no encontrado"));

        interesado.setRestringido(true);
        interesadoRepository.save(interesado);
    }

    @GetMapping("/restringidos")
    public List<Interesado> getRestringidos() {
        return interesadoService.listarRestringidos();
    }

    @GetMapping("/{legajo}/pruebas")
    public List<PruebaDTO> obtenerPruebas(@PathVariable Long legajo) {
        return pruebaClient.obtenerPruebasPorEmpleado(legajo);
    }

}
