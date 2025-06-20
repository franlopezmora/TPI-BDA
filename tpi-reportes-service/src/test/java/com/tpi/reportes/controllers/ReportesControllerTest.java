package com.tpi.reportes.controllers;

import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import com.tpi.reportes.services.ReportesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.List.of;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ReportesController.class)
class ReportesControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReportesService reportesService;

    @Test
    @DisplayName("GET /reportes/incidentes → lista de DTOs")
    void getIncidentes_returnsList() throws Exception {
        IncidenteDTO dto = new IncidenteDTO();
        dto.setId(1L);
        dto.setTipo("TIPO");
        given(reportesService.obtenerIncidentes())
                .willReturn(of(dto));

        mvc.perform(get("/reportes/incidentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipo").value("TIPO"));
    }

    @Test
    @DisplayName("GET /reportes/incidentes/empleado/{legajo} → filtra por legajo")
    void getIncidentesPorEmpleado_callsServiceWithLegajo() throws Exception {
        given(reportesService.obtenerIncidentesPorEmpleado(42L))
                .willReturn(of());
        mvc.perform(get("/reportes/incidentes/empleado/{legajo}", 42L))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /reportes/kilometros-recorridos → devuelve un double")
    void getKmRecorridos_returnsValue() throws Exception {
        given(reportesService.calcularKilometrosRecorridos(eq(5L), anyString(), anyString()))
                .willReturn(123.45);
        mvc.perform(get("/reportes/kilometros-recorridos")
                        .param("idVehiculo","5")
                        .param("desde","2025-01-01")
                        .param("hasta","2025-01-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("123.45"));
    }

    @Test
    @DisplayName("GET /reportes/pruebas/vehiculo/{idVehiculo} → lista de pruebas")
    void getPruebasPorVehiculo_returnsList() throws Exception {
        PruebaDTO prueba = new PruebaDTO();
        prueba.setId(99L);
        given(reportesService.obtenerPruebasPorVehiculo(7L))
                .willReturn(of(prueba));

        mvc.perform(get("/reportes/pruebas/vehiculo/{idVehiculo}", 7L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(99));
    }
}
