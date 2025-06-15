package com.tpi.pruebas.clients;

import com.tpi.pruebas.dtos.EmpleadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="admin-service", url = "${services.admin.url}")
public interface EmpleadoClient {
    @GetMapping("/empleados/{id}")
    EmpleadoDTO getEmpleado(@PathVariable("id") Long id);
}
