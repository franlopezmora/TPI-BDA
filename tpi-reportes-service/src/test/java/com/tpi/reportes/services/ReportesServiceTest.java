package com.tpi.reportes.services;

import com.tpi.reportes.clients.PosicionesClient;
import com.tpi.reportes.clients.PruebasClient;
import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PosicionDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import com.tpi.reportes.dtos.VehiculoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReportesServiceTest {

    private PruebasClient pruebasClient;
    private PosicionesClient posicionesClient;
    private ReportesService reportesService;

    @BeforeEach
    void setUp() {
        pruebasClient = mock(PruebasClient.class);
        posicionesClient = mock(PosicionesClient.class);
        reportesService = new ReportesService(posicionesClient, pruebasClient);
    }

    @Test
    void testObtenerIncidentes() {
        IncidenteDTO incidente1 = new IncidenteDTO();
        IncidenteDTO incidente2 = new IncidenteDTO();
        when(pruebasClient.obtenerTodosIncidentes()).thenReturn(Arrays.asList(incidente1, incidente2));

        List<IncidenteDTO> result = reportesService.obtenerIncidentes();

        assertEquals(2, result.size());
        verify(pruebasClient, times(1)).obtenerTodosIncidentes();
    }

    @Test
    void testObtenerIncidentesPorEmpleado() {
        IncidenteDTO incidente1 = new IncidenteDTO();
        incidente1.setLegajoEmpleado(100L);
        IncidenteDTO incidente2 = new IncidenteDTO();
        incidente2.setLegajoEmpleado(200L);
        IncidenteDTO incidente3 = new IncidenteDTO();
        incidente3.setLegajoEmpleado(100L);

        when(pruebasClient.obtenerTodosIncidentes())
                .thenReturn(Arrays.asList(incidente1, incidente2, incidente3));

        List<IncidenteDTO> result = reportesService.obtenerIncidentesPorEmpleado(100L);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(i -> i.getLegajoEmpleado().equals(100L)));
    }

    @Test
    void testCalcularKilometrosRecorridos() {
        PosicionDTO p1 = new PosicionDTO();
        p1.setIdVehiculo(10L);
        p1.setFechaHora(LocalDateTime.of(2024, 6, 1, 10, 0));
        p1.setLatitud(0);
        p1.setLongitud(0);

        PosicionDTO p2 = new PosicionDTO();
        p2.setIdVehiculo(10L);
        p2.setFechaHora(LocalDateTime.of(2024, 6, 1, 11, 0));
        p2.setLatitud(0);
        p2.setLongitud(1);

        when(posicionesClient.obtenerTodas()).thenReturn(Arrays.asList(p1, p2));

        double km = reportesService.calcularKilometrosRecorridos(10L,
                "2024-06-01T09:00:00", "2024-06-01T12:00:00");

        assertTrue(km > 100 && km < 112, "La distancia debe ser cercana a 111km, es: " + km);
    }

    @Test
    void testCalcularKilometrosRecorridosSinDatos() {
        when(posicionesClient.obtenerTodas()).thenReturn(Collections.emptyList());

        double km = reportesService.calcularKilometrosRecorridos(10L,
                "2024-06-01T09:00:00", "2024-06-01T12:00:00");

        assertEquals(0.0, km, "Si no hay posiciones, la distancia debe ser cero");
    }

    @Test
    void testObtenerPruebasPorVehiculo() {
        PruebaDTO prueba1 = new PruebaDTO();
        VehiculoDTO v1 = new VehiculoDTO();
        v1.setId(15L);
        prueba1.setVehiculo(v1);

        PruebaDTO prueba2 = new PruebaDTO();
        VehiculoDTO v2 = new VehiculoDTO();
        v2.setId(25L);
        prueba2.setVehiculo(v2);

        PruebaDTO prueba3 = new PruebaDTO();
        VehiculoDTO v3 = new VehiculoDTO();
        v3.setId(15L);
        prueba3.setVehiculo(v3);

        when(pruebasClient.obtenerTodas()).thenReturn(Arrays.asList(prueba1, prueba2, prueba3));

        List<PruebaDTO> result = reportesService.obtenerPruebasPorVehiculo(15L);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getVehiculo().getId().equals(15L)));
    }
}
