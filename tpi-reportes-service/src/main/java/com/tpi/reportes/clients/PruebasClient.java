package com.tpi.reportes.clients;

import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "pruebas", url = "http://tpi-pruebas-service:8085")
public interface PruebasClient {

    @GetMapping("/pruebas")
    List<PruebaDTO> obtenerTodas();

    @GetMapping("/incidentes")
    List<IncidenteDTO> obtenerTodosIncidentes();
}
