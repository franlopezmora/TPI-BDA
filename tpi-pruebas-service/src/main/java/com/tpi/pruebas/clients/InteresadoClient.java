package com.tpi.pruebas.clients;

import com.tpi.pruebas.dtos.InteresadoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="empleado-service", url="${services.admin.url}")
public interface InteresadoClient {
    @GetMapping("/interesados/{id}")
    InteresadoDTO getInteresado(@PathVariable("id") Long id);
}
