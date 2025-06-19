package com.tpi.reportes.clients;

import com.tpi.reportes.dtos.PosicionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "vehiculos", url = "http://tpi-vehiculos-service:8082")
public interface PosicionesClient {
    @GetMapping("/posiciones")
    List<PosicionDTO> obtenerTodas();
}
