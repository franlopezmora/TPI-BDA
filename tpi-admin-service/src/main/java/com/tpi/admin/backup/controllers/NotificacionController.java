//package com.tpi.admin.controllers;
//
//import com.tpi.admin.entities.Notificacion;
//import com.tpi.admin.services.NotificacionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/notificaciones")
//public class NotificacionController {
//
//    @Autowired
//    private NotificacionService notificacionService;
//
//    @GetMapping
//    public List<Notificacion> obtenerTodas() {
//        return notificacionService.obtenerTodas();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Integer id) {
//        return notificacionService.obtenerPorId(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion notificacion) {
//        Notificacion creada = notificacionService.crear(notificacion);
//        return ResponseEntity.ok(creada);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Notificacion> actualizar(@PathVariable Integer id, @RequestBody Notificacion notificacion) {
//        return notificacionService.actualizar(id, notificacion)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
//        if (notificacionService.eliminar(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
