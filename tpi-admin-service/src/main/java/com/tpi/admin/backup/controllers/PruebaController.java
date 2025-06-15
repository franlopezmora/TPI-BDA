//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.Prueba;
//import com.tpi.admin.services.PruebaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/pruebas")
//public class PruebaController {
//
//    @Autowired
//    private PruebaService pruebaService;
//
//    @GetMapping
//    public List<Prueba> getAll() {
//        return pruebaService.listarPruebas();
//    }
//
//    @PostMapping
//    public Prueba create(@RequestBody Prueba prueba) {
//        return pruebaService.crearPrueba(prueba);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Prueba> getById(@PathVariable Long id) {
//        return pruebaService.obtenerPruebaPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Prueba> update(@PathVariable Long id, @RequestBody Prueba pruebaActualizada) {
//        try {
//            Prueba actualizada = pruebaService.actualizarPrueba(id, pruebaActualizada);
//            return ResponseEntity.ok(actualizada);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        pruebaService.eliminarPrueba(id);
//        return ResponseEntity.noContent().build();
//    }
//}
