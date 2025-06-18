package com.tpi.reportes.services;

import com.tpi.reportes.clients.NotificacionesClient;
import com.tpi.reportes.dtos.IncidenteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportesService {

    private final NotificacionesClient notificacionesClient;

    public ReportesService(NotificacionesClient notificacionesClient) {
        this.notificacionesClient = notificacionesClient;
    }

    public List<IncidenteDTO> obtenerIncidentes() {
        // Llama al microservicio de notificaciones
        return notificacionesClient.obtenerIncidentes();
    }

    public List<IncidenteDTO> obtenerIncidentesPorEmpleado(Long legajo) {
        List<IncidenteDTO> todos = notificacionesClient.obtenerIncidentes();
        return todos.stream()
                .filter(i -> i.getIdPrueba() != null && i.getNombreEmpleado() != null)
                .filter(i -> i.getLegajoEmpleado() != null && i.getLegajoEmpleado().equals(legajo))
                .toList();
    }
}
