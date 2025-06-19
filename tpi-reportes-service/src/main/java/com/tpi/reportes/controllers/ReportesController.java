package com.tpi.reportes.controllers;

import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import com.tpi.reportes.services.ReportesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/kilometros-recorridos")
    public ResponseEntity<Double> getKmRecorridos(
            @RequestParam Long idVehiculo,
            @RequestParam String desde,
            @RequestParam String hasta) {
        return ResponseEntity.ok(reportesService.calcularKilometrosRecorridos(idVehiculo, desde, hasta));
    }

    @GetMapping("/pruebas/vehiculo/{idVehiculo}")
    public ResponseEntity<List<PruebaDTO>> getPruebasPorVehiculo(@PathVariable Long idVehiculo) {
        return ResponseEntity.ok(reportesService.obtenerPruebasPorVehiculo(idVehiculo));
    }

}