package com.tpi.reportes.services;

import com.tpi.reportes.clients.PruebaClient;
import com.tpi.reportes.dtos.*;
import com.tpi.reportes.repositories.IncidenteRepository;
import com.tpi.reportes.repositories.TipoIncidenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReportesService {

    private final IncidenteRepository incidenteRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;
    private final PruebaClient pruebasClient;

    public ReportesService(
            IncidenteRepository incidenteRepository,
            TipoIncidenteRepository tipoIncidenteRepository,
            PruebaClient pruebasClient) {
        this.incidenteRepository = incidenteRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
        this.pruebasClient = pruebasClient;
    }

    public List<IncidenteDTO> obtenerTodosLosIncidentes() {
        return incidenteRepository.findAll().stream()
                .map(i -> new IncidenteDTO(
                        i.getIncidenteId().getIdPrueba(),
                        i.getIncidenteId().getNroIncidente(),
                        i.getTipoIncidente().getNombre(),
                        i.getFechaHora()
                ))
                .collect(Collectors.toList());
    }

    public List<IncidenteVehiculoDTO> obtenerIncidentesPorVehiculo(Long idVehiculo) {
        return incidenteRepository.findAll().stream()
                .map(incidente -> {
                    Long idPrueba = incidente.getIncidenteId().getIdPrueba();
                    PruebaDTO prueba = pruebasClient.obtenerPruebaPorId(idPrueba);
                    if (prueba != null && prueba.getVehiculo().getId().equals(idVehiculo)) {
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

    public List<IncidenteEmpleadoDTO> obtenerIncidentesPorEmpleado(Long legajo) {
        return incidenteRepository.findAll().stream()
                .map(incidente -> {
                    Long idPrueba = incidente.getIncidenteId().getIdPrueba();
                    PruebaDTO prueba = pruebasClient.obtenerPruebaPorId(idPrueba);
                    if (prueba != null && prueba.getEmpleado().getLegajo().equals(legajo)) {
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

    //public KilometrosVehiculoDTO obtenerKilometrosPorVehiculoEnPeriodo(Long idVehiculo, LocalDateTime desde, LocalDateTime hasta) {
        // cuando haya acceso a las posiciones. => marianelson
       // return new KilometrosVehiculoDTO(idVehiculo, 0.0); // Placeholder
  //  }
}
