package com.tpi.notificaciones.controllers;

import com.tpi.notificaciones.dtos.NotificacionDTO;
import com.tpi.notificaciones.services.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody NotificacionDTO dto) {
        notificacionService.enviarNotificacion(dto);
        return ResponseEntity.ok("Notificación enviada a Discord");
    }
}
