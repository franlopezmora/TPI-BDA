package com.tpi.admin.controllers;

import com.tpi.admin.entities.Notificacion;
import com.tpi.admin.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @GetMapping
    public List<Notificacion> getAll() {
        return notificacionRepository.findAll();
    }

    @PostMapping
    public Notificacion create(@RequestBody Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }
}