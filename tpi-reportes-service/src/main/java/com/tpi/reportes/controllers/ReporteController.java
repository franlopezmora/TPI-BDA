package com.tpi.reportes.controllers;


import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.IncidenteEmpleadoDTO;
import com.tpi.reportes.dtos.IncidenteVehiculoDTO;
import com.tpi.reportes.dtos.PruebaDTO;

import com.tpi.reportes.services.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService svc;

    public ReporteController(ReporteService svc) {
        this.svc = svc;
    }

    @GetMapping("/kilometros/{vehiculoId}")
    public double getKm(
            @PathVariable Integer vehiculoId,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime hasta
    ) {
        return svc.calcularKilometros(vehiculoId, desde, hasta);
    }

    @GetMapping("/pruebas/{vehiculoId}")
    public List<PruebaDTO> getPruebas(@PathVariable Integer vehiculoId) {
        return svc.obtenerPruebasPorVehiculo(vehiculoId);
    }

    @GetMapping("/incidentes")
    public List<IncidenteDTO> getTodosLosIncidentes(){
        return svc.obtenerTodosLosIncidentes();
    }
    @GetMapping("/incidentes/vehiculo/{idVehiculo}")
    public List<IncidenteVehiculoDTO> getIncidentesPorVehiculo(@PathVariable Long idVehiculo){
        return svc.obtenerIncidentesPorVehiculo(idVehiculo);
    }
    @GetMapping("/incidentes/empleado/{legajo}")
    public List <IncidenteEmpleadoDTO> getIncidentesPorEmpleado(@PathVariable Long legajo){
        return svc.obtenerIncidentesPorEmpleado(legajo);
    }
}