package com.tpi.reportes.controllers;

import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.services.ReportesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    @GetMapping("/incidentes")
    public List<IncidenteDTO> getIncidentes() {
        return reportesService.obtenerIncidentes();
    }

    @GetMapping("/incidentes/empleado/{legajo}")
    public List<IncidenteDTO> getIncidentesPorEmpleado(@PathVariable Long legajo) {
        return reportesService.obtenerIncidentesPorEmpleado(legajo);
    }
}