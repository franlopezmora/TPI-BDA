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

@Service
public class ReportesService {

    private final PosicionesClient posicionesClient;
    private final PruebasClient pruebasClient;

    public ReportesService(PosicionesClient posicionesClient, PruebasClient pruebasClient) {
        this.posicionesClient = posicionesClient;
        this.pruebasClient = pruebasClient;
    }

    public List<IncidenteDTO> obtenerIncidentes() {
        return pruebasClient.obtenerTodosIncidentes();
    }

    public List<IncidenteDTO> obtenerIncidentesPorEmpleado(Long legajo) {
        return pruebasClient.obtenerTodosIncidentes().stream()
                .filter(i -> i.getLegajoEmpleado() != null && i.getLegajoEmpleado().equals(legajo))
                .toList();
    }

    public double calcularKilometrosRecorridos(Long idVehiculo, String desdeStr, String hastaStr) {
        LocalDateTime desde = LocalDateTime.parse(desdeStr);
        LocalDateTime hasta = LocalDateTime.parse(hastaStr);

        List<PosicionDTO> posiciones = posicionesClient.obtenerTodas().stream()
                .filter(p -> p.getIdVehiculo().equals(idVehiculo))
                .filter(p -> !p.getFechaHora().isBefore(desde) && !p.getFechaHora().isAfter(hasta))
                .sorted(Comparator.comparing(PosicionDTO::getFechaHora))
                .toList();

        double total = 0;
        for (int i = 1; i < posiciones.size(); i++) {
            PosicionDTO a = posiciones.get(i - 1);
            PosicionDTO b = posiciones.get(i);
            total += distanciaEnKm(a.getLatitud(), a.getLongitud(), b.getLatitud(), b.getLongitud());
        }
        return total;
    }

    private double distanciaEnKm(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radio de la Tierra en km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<PruebaDTO> obtenerPruebasPorVehiculo(Long idVehiculo) {
        return pruebasClient.obtenerTodas().stream()
                .filter(p -> p.getVehiculo() != null && p.getVehiculo().getId().equals(idVehiculo))
                .toList();
    }

}
