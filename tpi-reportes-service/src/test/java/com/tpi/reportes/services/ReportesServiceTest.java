// src/main/java/com/tpi/reportes/services/ReportesServiceTest.java
package com.tpi.reportes.services;

import com.tpi.reportes.clients.PosicionesClient;
import com.tpi.reportes.clients.PruebasClient;
import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PosicionDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportesServiceTest {

    private final PruebasClient pruebasClient;
    private final PosicionesClient posicionesClient;

    public ReportesServiceTest (PruebasClient pruebasClient,
                                PosicionesClient posicionesClient) {
        this.pruebasClient = pruebasClient;
        this.posicionesClient = posicionesClient;
    }

    public List<IncidenteDTO> obtenerIncidentes() {
        return pruebasClient.obtenerTodosIncidentes();
    }

    public List<IncidenteDTO> obtenerIncidentesPorEmpleado(Long legajo) {
        return pruebasClient.obtenerTodosIncidentes().stream()
                .filter(i -> i.getLegajoEmpleado() != null
                        && i.getLegajoEmpleado().equals(legajo))
                .collect(Collectors.toList());
    }

    public double calcularKilometrosRecorridos(Long idVehiculo,
                                               String desdeStr,
                                               String hastaStr) {
        LocalDateTime desde = LocalDateTime.parse(desdeStr);
        LocalDateTime hasta = LocalDateTime.parse(hastaStr);

        List<PosicionDTO> filtradas = posicionesClient.obtenerTodas().stream()
                .filter(p -> p.getIdVehiculo() != null
                        && p.getIdVehiculo().equals(idVehiculo))
                .filter(p -> !p.getFechaHora().isBefore(desde)
                        && !p.getFechaHora().isAfter(hasta))
                .sorted(Comparator.comparing(PosicionDTO::getFechaHora))
                .collect(Collectors.toList());

        if (filtradas.size() < 2) {
            return 0.0;
        }

        double total = 0;
        PosicionDTO prev = filtradas.get(0);
        for (int i = 1; i < filtradas.size(); i++) {
            PosicionDTO curr = filtradas.get(i);
            total += distanciaKm(
                    prev.getLatitud(), prev.getLongitud(),
                    curr.getLatitud(), curr.getLongitud()
            );
            prev = curr;
        }
        return total;
    }

    private double distanciaKm(double lat1, double lon1,
                               double lat2, double lon2) {
        final int R = 6_371; // radio terrestre en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    public List<PruebaDTO> obtenerPruebasPorVehiculo(Long idVehiculo) {
        return pruebasClient.obtenerTodas().stream()
                .filter(r -> r.getVehiculo() != null
                        && r.getVehiculo().getId().equals(idVehiculo))
                .collect(Collectors.toList());
    }
}
