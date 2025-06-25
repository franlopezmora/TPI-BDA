package com.tpi.admin.clients;

import com.tpi.admin.dtos.PruebaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "tpi-pruebas-service", url = "${url.pruebas-service}")
public interface PruebaClient {
    @GetMapping("/pruebas/empleado/{legajo}")
    List<PruebaDTO> obtenerPruebasPorEmpleado(@PathVariable("legajo") Long legajo);
}
