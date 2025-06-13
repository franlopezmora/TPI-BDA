package com.tpi.reportes.services;

import com.tpi.reportes.clients.PruebaClient;
import com.tpi.reportes.dtos.*;
import com.tpi.reportes.entities.Incidente;
import com.tpi.reportes.repositories.IncidenteRepository;
import com.tpi.reportes.repositories.TipoIncidenteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final RestTemplate rest;
    private final String adminBase;
    private final IncidenteRepository incidenteRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;
    private final PruebaClient pruebasClient;

    public ReporteServiceImpl(RestTemplate rest,
                              @Value("${services.admin.url}") String adminBase, IncidenteRepository incidenteRepository, TipoIncidenteRepository tipoIncidenteRepository, PruebaClient pruebasClient) {
        this.rest      = rest;
        this.adminBase = adminBase;
        this.incidenteRepository = incidenteRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
        this.pruebasClient = pruebasClient;
    }

    @Override
    public List<IncidenteDTO> obtenerTodosLosIncidentes(){
        return incidenteRepository.findAll().stream()
                .map(i-> new IncidenteDTO(
                        i.getIncidenteId().getIdPrueba(),
                        i.getIncidenteId().getNroIncidente(),
                        i.getTipoIncidente().getNombre(),
                        i.getFechaHora()
                        ))
                .collect(Collectors.toList());

    }
    @Override
    public  List<IncidenteVehiculoDTO> obtenerIncidentesPorVehiculo(Long idVehiculo){
        return incidenteRepository.findAll().stream()
                .map(incidente->{
                    Long idPrueba = incidente.getIncidenteId().getIdPrueba();
                    PruebaDTO prueba = pruebasClient.obtenerPruebaPorId(idPrueba);
                    if(prueba != null && prueba.getVehiculo().getId().equals(idVehiculo)){
                        return new IncidenteVehiculoDTO(
                                incidente.getIncidenteId().getNroIncidente(),
                                incidente.getTipoIncidente().getNombre(),
                                incidente.getFechaHora(),
                                prueba.getVehiculo().getPatente(),
                                prueba.getEmpleado().getNombreCompleto(),
                                prueba.getInteresado().getNombreCompleto(),
                                prueba.getComentario()
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<IncidenteEmpleadoDTO> obtenerIncidentesPorEmpleado(Long legajo){
        return incidenteRepository.findAll().stream()
                .map(incidente->{
                    Long idPrueba = incidente.getIncidenteId().getIdPrueba();
                    PruebaDTO prueba = pruebasClient.obtenerPruebaPorId(idPrueba);
                    if (prueba != null && prueba.getEmpleado().getLegajo().equals(legajo)){
                        return new IncidenteEmpleadoDTO(
                                idPrueba,
                                incidente.getIncidenteId().getNroIncidente(),
                                incidente.getTipoIncidente().getNombre(),
                                incidente.getFechaHora(),
                                prueba.getVehiculo().getId(),
                                prueba.getInteresado().getNombre()
                        );
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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