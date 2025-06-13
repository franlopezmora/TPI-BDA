//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.Marca;
//import com.tpi.admin.services.MarcaService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/marcas")
//public class MarcaController {
//
//    @Autowired
//    private MarcaService marcaService;
//
//    @GetMapping
//    public List<Marca> obtenerTodas() {
//        return marcaService.obtenerTodas();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Marca> obtenerPorId(@PathVariable Integer id) {
//        return marcaService.obtenerPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Marca> crear(@RequestBody Marca marca) {
//        Marca creada = marcaService.crear(marca);
//        return ResponseEntity.ok(creada);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Marca> actualizar(@PathVariable Integer id, @RequestBody Marca marca) {
//        return marcaService.actualizar(id, marca)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
//        if (marcaService.eliminar(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
