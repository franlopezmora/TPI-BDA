package com.tpi.reportes.services;

import com.tpi.reportes.dtos.PosicionDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final RestTemplate rest;
    private final String adminBase;

    public ReporteServiceImpl(RestTemplate rest,
                              @Value("${services.admin.url}") String adminBase) {
        this.rest      = rest;
        this.adminBase = adminBase;
    }

    @Override
    public double calcularKilometros(Integer vehiculoId,
                                     LocalDateTime desde,
                                     LocalDateTime hasta) {
        String url = String.format("%s/api/admin/posiciones?vehiculoId=%d&desde=%s&hasta=%s",
                adminBase, vehiculoId, desde, hasta);
        PosicionDTO[] arr = rest.getForObject(url, PosicionDTO[].class);
        List<PosicionDTO> pts = Arrays.asList(arr);
        pts.sort(Comparator.comparing(PosicionDTO::getFechaHora));
        double total = 0;
        for (int i = 1; i < pts.size(); i++) {
            total += pts.get(i).distanceTo(pts.get(i-1));
        }
        return total;
    }

    @Override
    public List<PruebaDTO> obtenerPruebasPorVehiculo(Integer vehiculoId) {
        String url = String.format("%s/api/admin/pruebas/vehiculo/%d",
                adminBase, vehiculoId);
        PruebaDTO[] arr = rest.getForObject(url, PruebaDTO[].class);
        return Arrays.asList(arr);
    }
}