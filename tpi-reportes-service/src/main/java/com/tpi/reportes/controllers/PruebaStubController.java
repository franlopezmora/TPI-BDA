package com.tpi.reportes.controllers;



import com.tpi.reportes.dtos.PruebaDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/admin/pruebas")
public class PruebaStubController {

    @GetMapping("/vehiculo/{vehiculoId}")
    public List<PruebaDTO> getPruebasPorVehiculo(
            @PathVariable Integer vehiculoId
    ) {
        PruebaDTO p1 = new PruebaDTO();
        p1.setId(100L);
        p1.setVehiculoId(vehiculoId);
        p1.setFechaHoraInicio(LocalDateTime.of(2025,6,1,10,0));
        p1.setFechaHoraFin(   LocalDateTime.of(2025,6,1,12,0));
        p1.setComentarios("Simulación Test A");
        p1.setEmpleadoId(1L);
        p1.setInteresadoId(1L);

        PruebaDTO p2 = new PruebaDTO();
        p2.setId(101L);
        p2.setVehiculoId(vehiculoId);
        p2.setFechaHoraInicio(LocalDateTime.of(2025,6,2,14,30));
        p2.setFechaHoraFin(   LocalDateTime.of(2025,6,2,15,45));
        p2.setComentarios("Simulación Test B");
        p2.setEmpleadoId(2L);
        p2.setInteresadoId(3L);

        return List.of(p1, p2);
    }
}