package com.tpi.reportes.clients;

import com.tpi.reportes.dtos.PruebaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PruebaClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://tpi-pruebas-service:8082";
    private PruebaClient(){
        this.restTemplate = new RestTemplate();
    }
    public PruebaDTO obtenerPruebaPorId(Long idPrueba){
        String url = baseUrl + "/pruebas/" + idPrueba;
        return restTemplate.getForObject(url, PruebaDTO.class);
    }
}
