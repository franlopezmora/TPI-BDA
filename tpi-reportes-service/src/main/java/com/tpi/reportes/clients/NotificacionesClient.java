package com.tpi.reportes.clients;

import com.tpi.reportes.dtos.IncidenteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "notificaciones-service", url = "http://localhost:8085")
public interface NotificacionesClient {

    @GetMapping("/notificaciones/incidentes")
    List<IncidenteDTO> obtenerIncidentes();
}
