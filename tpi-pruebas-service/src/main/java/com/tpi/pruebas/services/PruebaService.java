package com.tpi.pruebas.services;
import com.tpi.pruebas.clients.EmpleadoClient;
import com.tpi.pruebas.clients.InteresadoClient;
import com.tpi.pruebas.clients.VehiculoClient;
import com.tpi.pruebas.dtos.*;
import com.tpi.pruebas.entities.Incidente;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.entities.TipoIncidente;
import com.tpi.pruebas.repositories.IncidenteRepository;
import com.tpi.pruebas.repositories.PruebaRepository;
import com.tpi.pruebas.repositories.TipoIncidenteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PruebaService {
    private final PruebaRepository pruebaRepository;
    private final EmpleadoClient empleadoClient;
    private final InteresadoClient interesadoClient;
    private final VehiculoClient vehiculoClient;
    private final IncidenteRepository incidenteRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;
    public PruebaService(PruebaRepository pruebaRepository, EmpleadoClient empleadoClient, InteresadoClient interesadoClient, VehiculoClient vehiculoClient, IncidenteRepository incidenteRepository, TipoIncidenteRepository tipoIncidenteRepository){
        this.pruebaRepository = pruebaRepository;
        this.empleadoClient = empleadoClient;
        this.interesadoClient = interesadoClient;
        this.vehiculoClient = vehiculoClient;
        this.incidenteRepository = incidenteRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
    }

    public List<PruebaDTO> listar(){
        return pruebaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    public Optional<PruebaDTO> obtenerPorId(Long id){
        return pruebaRepository.findById(id)
                .map(this::toDto);
    }
    public PruebaDTO crear(Prueba prueba){
        InteresadoDTO interesado = interesadoClient.getInteresado(prueba.getIdInteresado());
        if(interesado.getRestringido() != null && interesado.getRestringido()){
            throw  new IllegalArgumentException("El interesado está restringido y no puede realizar pruebas en esta agencia.");
        }
        if (interesado.getFechaVencimientoLicencia() == null || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())){
           throw  new IllegalArgumentException("La licencia del interesado está vencida");
        }

        //FALTA VALIDAR EL VEHÍCULO.
        //VehiculoDto vehiculo = vehiculoClient.obtenerVehiculo(prueba.getVehiculo().getId().LongValue());
        // if (vehiculo.getEnPrueba() != null && vehiculo.getEnprueba()){
        // throw new IllegalArgumentException("El vehiculo ya se encuentra en prueba")}
        Prueba saved = pruebaRepository.save(prueba);
        return toDto(saved);
    }
    public boolean eliminar (Long id){
        if (pruebaRepository.existsById(id)) {
            pruebaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PruebaDTO toDto(Prueba e){
        EmpleadoDTO emp = empleadoClient.getEmpleado(e.getIdEmpleado());
        InteresadoDTO intz = interesadoClient.getInteresado(e.getIdInteresado());
        VehiculoDTO veh = vehiculoClient.obtenerVehiculo(e.getIdVehiculo());
        PruebaDTO dto = new PruebaDTO();
        dto.setId(e.getId());
        dto.setFechaHoraInicio(e.getFechaHoraInicio());
        dto.setFechaHoraFin(e.getFechaHoraFin());
        dto.setComentario(e.getComentarios());
        dto.setEmpleado(emp);
        dto.setInteresado(intz);
        dto.setVehiculo(veh);
        return dto;
    }

    public void registrarIncidente(Long idPrueba, String nombreTipoIncidente) {
        Prueba prueba = pruebaRepository.findById(idPrueba)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        TipoIncidente tipo = tipoIncidenteRepository.findByNombreIncidente(nombreTipoIncidente)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de incidente no válido"));

        Incidente incidente = new Incidente();
        incidente.setPrueba(prueba);
        incidente.setTipoIncidente(tipo);
        incidente.setFechaHora(LocalDateTime.now());

        incidenteRepository.save(incidente);
    }

    public List<IncidenteDTO> listarIncidentes() {
        return incidenteRepository.findAll().stream().map(incidente -> {
            IncidenteDTO dto = new IncidenteDTO();
            dto.setId(incidente.getId());
            dto.setFecha(incidente.getFechaHora());

            if (incidente.getTipoIncidente() != null) {
                dto.setTipo(incidente.getTipoIncidente().getNombreIncidente());
            }

            Prueba prueba = incidente.getPrueba();
            if (prueba != null) {
                dto.setIdPrueba(prueba.getId());

                EmpleadoDTO emp = empleadoClient.getEmpleado(prueba.getIdEmpleado());
                InteresadoDTO intz = interesadoClient.getInteresado(prueba.getIdInteresado());

                if (emp != null) {
                    dto.setLegajoEmpleado(emp.getLegajo());
                    dto.setNombreEmpleado(emp.getNombre());
                    dto.setApellidoEmpleado(emp.getApellido());
                    dto.setTelefono(emp.getTelefonoContacto());
                }

                if (intz != null) {
                    dto.setNombreInteresado(intz.getNombre());
                    dto.setApellidoInteresado(intz.getApellido());
                }

                dto.setMensaje("Incidente detectado durante la prueba"); // mensaje estático o podés ajustarlo
            }

            return dto;
        }).toList();
    }
}