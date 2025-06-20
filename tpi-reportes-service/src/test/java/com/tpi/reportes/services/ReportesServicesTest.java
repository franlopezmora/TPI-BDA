package com.tpi.reportes.services;

import com.tpi.reportes.clients.PosicionesClient;
import com.tpi.reportes.clients.PruebasClient;
import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PosicionDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import com.tpi.reportes.dtos.VehiculoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportesServiceTest {

    @Mock
    private PosicionesClient posicionesClient;

    @Mock
    private PruebasClient pruebasClient;

    @InjectMocks
    private ReportesService reportesService;

    @Test
    void testObtenerIncidentes() {
        List<IncidenteDTO> esperados = List.of(new IncidenteDTO(), new IncidenteDTO());
        when(pruebasClient.obtenerTodosIncidentes()).thenReturn(esperados);

        List<IncidenteDTO> resultado = reportesService.obtenerIncidentes();

        assertSame(esperados, resultado);
        verify(pruebasClient, times(1)).obtenerTodosIncidentes();
    }

    @Test
    void testObtenerIncidentesPorEmpleado() {
        IncidenteDTO i1 = new IncidenteDTO();
        i1.setLegajoEmpleado(1L);
        IncidenteDTO i2 = new IncidenteDTO();
        i2.setLegajoEmpleado(2L);
        IncidenteDTO i3 = new IncidenteDTO();
        i3.setLegajoEmpleado(null);

        when(pruebasClient.obtenerTodosIncidentes()).thenReturn(List.of(i1, i2, i3));

        List<IncidenteDTO> resultado = reportesService.obtenerIncidentesPorEmpleado(1L);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(i1));
        assertFalse(resultado.contains(i2));
        assertFalse(resultado.contains(i3));
        verify(pruebasClient, times(1)).obtenerTodosIncidentes();
    }

    @Test
    void testCalcularKilometrosRecorridos_SinPosiciones() {
        when(posicionesClient.obtenerTodas()).thenReturn(List.of());

        double km = reportesService.calcularKilometrosRecorridos(
                42L, "2025-06-01T00:00:00", "2025-06-02T00:00:00");

        assertEquals(0.0, km, 0.0001);
        verify(posicionesClient, times(1)).obtenerTodas();
    }

    @Test
    void testCalcularKilometrosRecorridos_DosPosiciones() {
        PosicionDTO p1 = new PosicionDTO();
        p1.setIdVehiculo(5L);
        p1.setFechaHora(LocalDateTime.parse("2025-06-10T10:00:00"));
        p1.setLatitud(0.0);
        p1.setLongitud(0.0);

        PosicionDTO p2 = new PosicionDTO();
        p2.setIdVehiculo(5L);
        p2.setFechaHora(LocalDateTime.parse("2025-06-10T11:00:00"));
        p2.setLatitud(0.0);
        p2.setLongitud(1.0);

        when(posicionesClient.obtenerTodas()).thenReturn(List.of(p1, p2));

        double km = reportesService.calcularKilometrosRecorridos(
                5L, "2025-06-10T00:00:00", "2025-06-11T00:00:00");

        // Aproximadamente 111 km entre (0,0) y (0,1)
        assertEquals(111.19, km, 0.5);
        verify(posicionesClient, times(1)).obtenerTodas();
    }

    @Test
    void testObtenerPruebasPorVehiculo() {
        PruebaDTO r1 = new PruebaDTO();
        VehiculoDTO v1 = new VehiculoDTO();
        v1.setId(100L);
        r1.setVehiculo(v1);

        PruebaDTO r2 = new PruebaDTO();
        VehiculoDTO v2 = new VehiculoDTO();
        v2.setId(200L);
        r2.setVehiculo(v2);

        PruebaDTO r3 = new PruebaDTO();
        r3.setVehiculo(null);

        when(pruebasClient.obtenerTodas()).thenReturn(List.of(r1, r2, r3));

        List<PruebaDTO> resultado = reportesService.obtenerPruebasPorVehiculo(100L);

        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(r1));
        assertFalse(resultado.contains(r2));
        assertFalse(resultado.contains(r3));
        verify(pruebasClient, times(1)).obtenerTodas();
    }
}
