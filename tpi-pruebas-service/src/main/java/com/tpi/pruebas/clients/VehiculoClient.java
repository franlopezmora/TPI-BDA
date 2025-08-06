package com.tpi.pruebas.clients;

import com.tpi.pruebas.dtos.VehiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vehiculos-service", url = "${services.vehiculos.url}", path = "/vehiculos")
public interface VehiculoClient {
    @GetMapping("/{id}")
    VehiculoDTO obtenerVehiculo(@PathVariable("id") Long id);

    @GetMapping("/{id}/all")
    VehiculoDTO obtenerVehiculoInclusoInactivo(@PathVariable Long id);
}
