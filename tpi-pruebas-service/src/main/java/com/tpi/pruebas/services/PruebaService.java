package com.tpi.pruebas.services;
import com.tpi.pruebas.clients.*;
import com.tpi.pruebas.dtos.*;
import com.tpi.pruebas.entities.Incidente;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.entities.TipoIncidente;
import com.tpi.pruebas.repositories.IncidenteRepository;
import com.tpi.pruebas.repositories.PruebaRepository;
import com.tpi.pruebas.repositories.TipoIncidenteRepository;
import com.tpi.pruebas.exception.InteresadoRestringidoException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PruebaService {
    private static final Logger log = LoggerFactory.getLogger(PruebaService.class);

    private final PruebaRepository pruebaRepository;
    private final EmpleadoClient empleadoClient;
    private final InteresadoClient interesadoClient;
    private final VehiculoClient vehiculoClient;
    private final IncidenteRepository incidenteRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;
    private final NotificacionClient notificacionClient;
    private final ConfiguracionClient configuracionClient;
    public PruebaService(PruebaRepository pruebaRepository, EmpleadoClient empleadoClient, InteresadoClient interesadoClient, VehiculoClient vehiculoClient, IncidenteRepository incidenteRepository, TipoIncidenteRepository tipoIncidenteRepository, NotificacionClient notificacionClient, ConfiguracionClient configuracionClient){
        this.pruebaRepository = pruebaRepository;
        this.empleadoClient = empleadoClient;
        this.interesadoClient = interesadoClient;
        this.vehiculoClient = vehiculoClient;
        this.incidenteRepository = incidenteRepository;
        this.tipoIncidenteRepository = tipoIncidenteRepository;
        this.notificacionClient = notificacionClient;
        this.configuracionClient = configuracionClient;

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
        VehiculoDTO veh = vehiculoClient.obtenerVehiculoInclusoInactivo(prueba.getIdVehiculo());
        if (veh == null || Boolean.FALSE.equals(veh.getActivo())) {
            throw new IllegalArgumentException(
                    "El vehículo con id " + prueba.getIdVehiculo() + " está dado de baja y no puede iniciar pruebas."
            );
        }

        InteresadoDTO interesado = interesadoClient.getInteresado(prueba.getIdInteresado());
        if(interesado.getRestringido() != null && interesado.getRestringido()){
            throw  new IllegalArgumentException("El interesado está restringido y no puede realizar pruebas en esta agencia.");
        }
        if (interesado.getFechaVencimientoLicencia() == null || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())){
           throw  new IllegalArgumentException("La licencia del interesado está vencida");
        }

        // Validar si el vehículo está en una prueba activa
        if (pruebaRepository.findByIdVehiculoAndFechaHoraFinIsNull(prueba.getIdVehiculo()).isPresent()) {
            throw new IllegalArgumentException("El vehículo ya se encuentra en una prueba activa.");
        }

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

    public PruebaDTO actualizar(Prueba prueba) {
        VehiculoDTO veh = vehiculoClient.obtenerVehiculoInclusoInactivo(prueba.getIdVehiculo());
        if (veh == null || Boolean.FALSE.equals(veh.getActivo())) {
            throw new IllegalArgumentException(
                    "El vehículo con id " + prueba.getIdVehiculo() + " está dado de baja y no puede actualizar pruebas."
            );
        }

        // 1) Existe?
        if (!pruebaRepository.existsById(prueba.getId())) {
            throw new IllegalArgumentException("Prueba con id " + prueba.getId() + " no encontrada");
        }

        // 2) Validaciones de negocio (idénticas a crear)
        InteresadoDTO interesado = interesadoClient.getInteresado(prueba.getIdInteresado());
        if (Boolean.TRUE.equals(interesado.getRestringido())) {
            throw new InteresadoRestringidoException(interesado.getId());
        }
        if (interesado.getFechaVencimientoLicencia() == null
                || interesado.getFechaVencimientoLicencia().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La licencia del interesado está vencida.");
        }

        // Asegurar que el vehículo no esté en otra prueba activa distinta de ésta
        boolean otroEnPrueba = pruebaRepository
                .findByIdVehiculoAndFechaHoraFinIsNull(prueba.getIdVehiculo())
                .filter(p -> !p.getId().equals(prueba.getId()))
                .isPresent();
        if (otroEnPrueba) {
            throw new IllegalArgumentException("El vehículo ya está en otra prueba activa.");
        }

        // 3) Persisto y retorno DTO
        Prueba saved = pruebaRepository.save(prueba);
        return toDto(saved);
    }

    private PruebaDTO toDto(Prueba e){
        EmpleadoDTO emp = empleadoClient.getEmpleado(e.getIdEmpleado());
        InteresadoDTO intz = interesadoClient.getInteresado(e.getIdInteresado());
        VehiculoDTO veh = vehiculoClient
                .obtenerVehiculoInclusoInactivo(e.getIdVehiculo());
        PruebaDTO dto = new PruebaDTO();
        dto.setId(e.getId());
        dto.setFechaHoraInicio(e.getFechaHoraInicio());
        dto.setFechaHoraFin(e.getFechaHoraFin());
        dto.setComentario(e.getComentarios());
        dto.setEmpleado(emp);
        dto.setInteresado(intz);
        dto.setVehiculo(veh);
        dto.setIdVehiculo(e.getIdVehiculo());
        return dto;
    }

    public void registrarIncidente(Long idPrueba, String nombreTipoIncidente) {
        Prueba prueba = pruebaRepository.findById(idPrueba)
                .orElseThrow(() -> new IllegalArgumentException("Prueba no encontrada"));

        TipoIncidente tipo = tipoIncidenteRepository
                .findByNombreIncidente(nombreTipoIncidente)
                .orElseGet(() -> {
                    log.warn("Tipo '{}' no existe, omitiendo incidente", nombreTipoIncidente);
                    return null;
                });

        if (tipo == null) return;

        Incidente inc = new Incidente();
        inc.setIdPrueba(prueba.getId());
        inc.setTipoIncidente(tipo);
        inc.setFechaHora(LocalDateTime.now());

        incidenteRepository.save(inc);
    }

    public boolean existsById(Long id) {
        return pruebaRepository.existsById(id);
    }

    public List<IncidenteDTO> listarIncidentes() {
        return incidenteRepository.findAll()
                .stream()
                .map(this::mapIncidenteToDTO)
                .toList();
    }

    private IncidenteDTO mapIncidenteToDTO(Incidente inc) {
        IncidenteDTO dto = new IncidenteDTO();
        dto.setId(inc.getId());
        dto.setFecha(inc.getFechaHora());
        if (inc.getTipoIncidente() != null) {
            dto.setTipo(inc.getTipoIncidente().getNombreIncidente());
        }

        // 1) Asignamos siempre el FK
        Long idPrueba = inc.getIdPrueba();
        dto.setIdPrueba(idPrueba);

        // 2) Buscamos la Prueba de forma segura
        pruebaRepository.findById(idPrueba).ifPresentOrElse(prueba -> {
            // si existe, mapeamos sus datos al DTO
            EmpleadoDTO emp = empleadoClient.getEmpleado(prueba.getIdEmpleado());
            dto.setLegajoEmpleado(emp.getLegajo());
            InteresadoDTO intz = interesadoClient.getInteresado(prueba.getIdInteresado());
            dto.setNombreInteresado(intz.getNombre());
            dto.setMensaje("Incidente detectado durante la prueba");
        }, () -> {
            // si no existe (fue borrada), devolvemos un mensaje por fallback
            dto.setMensaje("Prueba eliminada, pero incidente registrado");
        });

        return dto;
    }

    public void validarPosicion(PosicionDTO dto) {
        // 1. Buscar prueba activa
        Optional<Prueba> pruebaOpt = pruebaRepository.findByIdVehiculoAndFechaHoraFinIsNull(dto.getIdVehiculo());
        if (pruebaOpt.isEmpty()) return;
        Prueba prueba = pruebaOpt.get();

        // 2. Traer datos relacionados
        InteresadoDTO interesado = interesadoClient.getInteresado(prueba.getIdInteresado());
        EmpleadoDTO empleado     = empleadoClient.getEmpleado(prueba.getIdEmpleado());

        // 3. Obtener configuración externa
        ConfiguracionDTO config = configuracionClient.obtenerConfiguracion();

        // ───> Aquí protegemos contra config.getZonasPeligrosas() == null
        List<ZonaPeligrosaDTO> zonas = config.getZonasPeligrosas() != null
                ? config.getZonasPeligrosas()
                : Collections.emptyList();

        // 4. Calcular distancia a la agencia
        double distancia = calcularDistancia(
                dto.getLatitud(), dto.getLongitud(),
                config.getUbicacionAgencia().getLatitud(),
                config.getUbicacionAgencia().getLongitud()
        );
        boolean fueraDeRadio = distancia > config.getRadioMaximoMetros();

        // 5. Si está de vuelta en la base, cerramos prueba
        if (distancia <= 0.0001) {
            prueba.setFechaHoraFin(dto.getFechaHora());
            pruebaRepository.save(prueba);
            return;
        }

        // 6. Validar zona peligrosa con la lista segura
        boolean enZonaPeligrosa = zonas.stream().anyMatch(zona -> {
            double dZona = calcularDistancia(
                    dto.getLatitud(), dto.getLongitud(),
                    zona.getCoordenadas().getLatitud(),
                    zona.getCoordenadas().getLongitud()
            );
            return dZona <= zona.getRadioMetros();
        });

        // 7. Si incumple radio o entra en zona, actuamos
        if (fueraDeRadio || enZonaPeligrosa) {
            String motivo = fueraDeRadio ? "Fuera de radio permitido" : "Ingreso a zona peligrosa";
            registrarIncidente(prueba.getId(), motivo);
            interesadoClient.restringirInteresado(interesado.getId());

            NotificacionDTO notif = new NotificacionDTO();
            notif.setTipo("Alerta");
            notif.setTelefono(empleado.getTelefonoContacto());
            notif.setIdPrueba(prueba.getId());
            notif.setMensaje("🚨 Vehículo en infracción: " + motivo);
            notif.setFecha(LocalDateTime.now().withNano(0));
            notificacionClient.enviarNotificacion(notif);
        }
    }


    public boolean vehiculoEstaEnPrueba(Long idVehiculo) {
        return pruebaRepository.findByIdVehiculoAndFechaHoraFinIsNull(idVehiculo).isPresent();
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double dx = lat1 - lat2;
        double dy = lon1 - lon2;
        return Math.sqrt(dx * dx + dy * dy);
    }


}