package com.tpi.vehiculos.clients;

import com.tpi.vehiculos.dtos.PosicionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pruebas", url = "${services.pruebas.url}")
public interface PruebaClient {

    @PostMapping("/pruebas/validar-posicion")
    void validarPosicion(@RequestBody PosicionDTO dto);
}