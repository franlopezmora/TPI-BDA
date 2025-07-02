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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PruebaServiceTest {
    @Mock PruebaRepository pruebaRepository;
    @Mock EmpleadoClient empleadoClient;
    @Mock InteresadoClient interesadoClient;
    @Mock VehiculoClient vehiculoClient;
    @Mock IncidenteRepository incidenteRepository;
    @Mock TipoIncidenteRepository tipoIncidenteRepository;

    @InjectMocks PruebaService pruebaService;

    private Prueba p;
    private EmpleadoDTO empDto;
    private InteresadoDTO intDto;
    private VehiculoDTO vehDto;

    @BeforeEach
    void setup() {
        p = new Prueba();
        p.setId(1L);
        p.setIdEmpleado(10L);
        p.setIdInteresado(20L);
        p.setIdVehiculo(30L);
        p.setFechaHoraInicio(LocalDateTime.now().minusHours(1));
        p.setFechaHoraFin(LocalDateTime.now());
        p.setComentarios("test");

        empDto = new EmpleadoDTO();
        empDto.setLegajo(123L);
        intDto = new InteresadoDTO();
        intDto.setRestringido(false);
        intDto.setFechaVencimientoLicencia(LocalDate.now().plusDays(1));
        vehDto = new VehiculoDTO();
        vehDto.setId(30L);
    }

    @Test
    void testListar() {
        when(pruebaRepository.findAll()).thenReturn(List.of(p));
        when(empleadoClient.getEmpleado(10L)).thenReturn(empDto);
        when(interesadoClient.getInteresado(20L)).thenReturn(intDto);
        when(vehiculoClient.obtenerVehiculo(30L)).thenReturn(vehDto);

        List<PruebaDTO> result = pruebaService.listar();

        assertEquals(1, result.size());
        PruebaDTO dto = result.get(0);
        assertEquals(empDto, dto.getEmpleado());
        assertEquals(intDto, dto.getInteresado());
        assertEquals(vehDto, dto.getVehiculo());
    }

    @Test
    void testObtenerPorId() {
        when(pruebaRepository.findById(1L)).thenReturn(Optional.of(p));
        when(empleadoClient.getEmpleado(anyLong())).thenReturn(empDto);
        when(interesadoClient.getInteresado(anyLong())).thenReturn(intDto);
        when(vehiculoClient.obtenerVehiculo(anyLong())).thenReturn(vehDto);

        Optional<PruebaDTO> opt = pruebaService.obtenerPorId(1L);
        assertTrue(opt.isPresent());
        assertEquals(empDto, opt.get().getEmpleado());
    }

    @Test
    void testCrear_Success() {
        Prueba nueva = new Prueba();
        nueva.setIdInteresado(20L);
        nueva.setIdEmpleado(10L);
        nueva.setIdVehiculo(30L);
        when(interesadoClient.getInteresado(20L)).thenReturn(intDto);
        when(pruebaRepository.save(nueva)).thenReturn(nueva);

        PruebaDTO dto = pruebaService.crear(nueva);
        assertNotNull(dto);
        assertEquals(nueva.getId(), dto.getId());
    }

    @Test
    void testCrear_Restringido() {
        intDto.setRestringido(true);
        when(interesadoClient.getInteresado(20L)).thenReturn(intDto);

        Prueba nueva = new Prueba();
        nueva.setIdInteresado(20L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> pruebaService.crear(nueva));
        assertTrue(ex.getMessage().contains("restringido"));
    }

    @Test
    void testCrear_LicenciaVencida() {
        intDto.setFechaVencimientoLicencia(LocalDate.now().minusDays(1));
        intDto.setRestringido(false);
        when(interesadoClient.getInteresado(20L)).thenReturn(intDto);

        Prueba nueva = new Prueba();
        nueva.setIdInteresado(20L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> pruebaService.crear(nueva));
        assertTrue(ex.getMessage().contains("vencida"));
    }

    @Test
    void testEliminar() {
        when(pruebaRepository.existsById(1L)).thenReturn(true);
        boolean ok = pruebaService.eliminar(1L);
        assertTrue(ok);
        verify(pruebaRepository).deleteById(1L);
    }

    @Test
    void testEliminar_NotFound() {
        when(pruebaRepository.existsById(2L)).thenReturn(false);
        assertFalse(pruebaService.eliminar(2L));
    }

    @Test
    void testRegistrarIncidente_Success() {
        TipoIncidente tipo = new TipoIncidente(); tipo.setId(5L); tipo.setNombreIncidente("Falla");
        when(pruebaRepository.findById(1L)).thenReturn(Optional.of(p));
        when(tipoIncidenteRepository.findByNombreIncidente("Falla")).thenReturn(Optional.of(tipo));

        pruebaService.registrarIncidente(1L, "Falla");
        verify(incidenteRepository).save(any(Incidente.class));
    }

    @Test
    void testRegistrarIncidente_NoPrueba() {
        when(pruebaRepository.findById(3L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,
                () -> pruebaService.registrarIncidente(3L, "X"));
    }

    @Test
    void testListarIncidentes() {
        Incidente inc = new Incidente();
        inc.setId(7L);
        inc.setFechaHora(LocalDateTime.now());
        TipoIncidente tipo = new TipoIncidente();
        tipo.setNombreIncidente("Error");
        inc.setTipoIncidente(tipo);
        inc.setPrueba(p);
        when(incidenteRepository.findAll()).thenReturn(List.of(inc));
        when(empleadoClient.getEmpleado(10L)).thenReturn(empDto);
        when(interesadoClient.getInteresado(20L)).thenReturn(intDto);

        List<IncidenteDTO> list = pruebaService.listarIncidentes();
        assertEquals(1, list.size());
        assertEquals("Error", list.get(0).getTipo());
        assertEquals(123L, list.get(0).getLegajoEmpleado());
    }
}
