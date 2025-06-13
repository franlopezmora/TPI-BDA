//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.Vehiculo;
//import com.tpi.admin.services.VehiculoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/vehiculos")
//public class VehiculoController {
//
//    @Autowired
//    private VehiculoService vehiculoService;
//
//    @GetMapping
//    public List<Vehiculo> getAll() {
//        return vehiculoService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Vehiculo> getById(@PathVariable Integer id) {
//        return vehiculoService.getById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Vehiculo> create(@RequestBody Vehiculo vehiculo) {
//        return ResponseEntity.ok(vehiculoService.save(vehiculo));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Vehiculo> update(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
//        try {
//            return ResponseEntity.ok(vehiculoService.update(id, vehiculo));
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//        vehiculoService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
