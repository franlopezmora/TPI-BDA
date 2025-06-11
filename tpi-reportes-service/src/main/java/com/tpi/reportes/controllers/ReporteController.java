package com.tpi.reportes.controllers;


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
}