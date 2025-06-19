package com.tpi.pruebas.clients;

import com.tpi.pruebas.dtos.ConfiguracionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "configuracion", url = "${services.configuracion.url}")
public interface ConfiguracionClient {
    @GetMapping("/api/agency-config")
    ConfiguracionDTO obtenerConfiguracion();
}
