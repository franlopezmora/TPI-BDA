package com.tpi.pruebas.clients;

import com.tpi.pruebas.dtos.NotificacionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificaciones", url = "${services.notificaciones.url}")
public interface NotificacionClient {

    @PostMapping("/notificaciones")
    void enviarNotificacion(@RequestBody NotificacionDTO dto);
}
