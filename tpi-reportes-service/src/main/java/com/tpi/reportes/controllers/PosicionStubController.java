package com.tpi.reportes.controllers;

import com.tpi.reportes.dtos.PosicionDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/posiciones")
public class PosicionStubController {

    @GetMapping
    public List<PosicionDTO> getPosiciones(
            @RequestParam Integer vehiculoId,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime hasta
    ) {
        // Simulamos tres puntos separados (10,20) → (10,21) → (11,21)
        PosicionDTO p1 = new PosicionDTO();
        p1.setId(1L);  p1.setVehiculoId(vehiculoId);
        p1.setFechaHora(desde.withHour(10).withMinute(0));
        p1.setLatitud(10.0); p1.setLongitud(20.0);

        PosicionDTO p2 = new PosicionDTO();
        p2.setId(2L);  p2.setVehiculoId(vehiculoId);
        p2.setFechaHora(desde.withHour(11).withMinute(0));
        p2.setLatitud(10.0); p2.setLongitud(21.0);

        PosicionDTO p3 = new PosicionDTO();
        p3.setId(3L);  p3.setVehiculoId(vehiculoId);
        p3.setFechaHora(desde.withHour(12).withMinute(0));
        p3.setLatitud(11.0); p3.setLongitud(21.0);

        return List.of(p1, p2, p3);
    }
}