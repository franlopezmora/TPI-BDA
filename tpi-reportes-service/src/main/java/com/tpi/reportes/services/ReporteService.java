package com.tpi.reportes.services;

import com.tpi.reportes.dtos.PruebaDTO;
import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {

    /**
     * (iii) Cantidad de kilómetros recorridos por un vehículo
     * en un periodo determinado.
     */
    double calcularKilometros(Integer vehiculoId,
                              LocalDateTime desde,
                              LocalDateTime hasta);

    /**
     * (iv) Detalle de todas las pruebas realizadas para un vehículo.
     */
    List<PruebaDTO> obtenerPruebasPorVehiculo(Integer vehiculoId);
}