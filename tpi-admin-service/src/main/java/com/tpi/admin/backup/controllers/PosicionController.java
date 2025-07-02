//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.Posicion;
//import com.tpi.admin.services.PosicionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/posiciones")
//public class PosicionController {
//
//    @Autowired
//    private PosicionService posicionService;
//
//    @GetMapping
//    public List<Posicion> obtenerTodas() {
//        return posicionService.obtenerTodas();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Posicion> obtenerPorId(@PathVariable Long id) {
//        return posicionService.obtenerPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Posicion> crear(@RequestBody Posicion posicion) {
//        Posicion creada = posicionService.crear(posicion);
//        return ResponseEntity.ok(creada);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Posicion> actualizar(@PathVariable Long id, @RequestBody Posicion posicion) {
//        return posicionService.actualizar(id, posicion)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
//        if (posicionService.eliminar(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
