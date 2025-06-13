//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.ZonaPeligrosa;
//import com.tpi.admin.services.ZonaPeligrosaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/zonas-peligrosas")
//public class ZonaPeligrosaController {
//
//    @Autowired
//    private ZonaPeligrosaService zonaPeligrosaService;
//
//    @GetMapping
//    public List<ZonaPeligrosa> obtenerTodas() {
//        return zonaPeligrosaService.obtenerTodas();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ZonaPeligrosa> obtenerPorId(@PathVariable Integer id) {
//        return zonaPeligrosaService.obtenerPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<ZonaPeligrosa> crear(@RequestBody ZonaPeligrosa zonaPeligrosa) {
//        ZonaPeligrosa creada = zonaPeligrosaService.crear(zonaPeligrosa);
//        return ResponseEntity.ok(creada);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ZonaPeligrosa> actualizar(@PathVariable Integer id, @RequestBody ZonaPeligrosa zonaPeligrosa) {
//        return zonaPeligrosaService.actualizar(id, zonaPeligrosa)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
//        if (zonaPeligrosaService.eliminar(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
