package com.tpi.reportes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.reportes.dtos.IncidenteDTO;
import com.tpi.reportes.dtos.PruebaDTO;
import com.tpi.reportes.services.ReportesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static java.util.List.of;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class ReportesControllerTest {

    @Mock(lenient = true)
    ReportesService reportesService;

    @InjectMocks
    ReportesController controller;

    MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    @DisplayName("GET /reportes/incidentes devuelve lista")
    void getIncidentes_returnsList() throws Exception {
        IncidenteDTO dto = new IncidenteDTO();
        dto.setId(1L);
        dto.setTipo("TIPO");
        given(reportesService.obtenerIncidentes()).willReturn(of(dto));

        mvc.perform(get("/reportes/incidentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tipo").value("TIPO"));
    }

    @Test
    @DisplayName("GET /reportes/incidentes/empleado/{legajo} filtra por legajo")
    void getIncidentesPorEmpleado_callsServiceWithLegajo() throws Exception {
        given(reportesService.obtenerIncidentesPorEmpleado(anyLong())).willReturn(of());
        mvc.perform(get("/reportes/incidentes/empleado/{legajo}", 42L))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /reportes/kilometros-recorridos devuelve un double")
    void getKmRecorridos_returnsValue() throws Exception {
        given(reportesService.calcularKilometrosRecorridos(anyLong(), anyString(), anyString())).willReturn(123.45);
        mvc.perform(get("/reportes/kilometros-recorridos")
                        .param("idVehiculo", "5")
                        .param("desde", "2025-01-01")
                        .param("hasta", "2025-01-31"))
                .andExpect(status().isOk())
                .andExpect(content().string("123.45"));
    }

    @Test
    @DisplayName("GET /reportes/pruebas/vehiculo/{idVehiculo} lista pruebas")
    void getPruebasPorVehiculo_returnsList() throws Exception {
        PruebaDTO prueba = new PruebaDTO();
        prueba.setId(99L);
        given(reportesService.obtenerPruebasPorVehiculo(anyLong())).willReturn(of(prueba));

        mvc.perform(get("/reportes/pruebas/vehiculo/{idVehiculo}", 7L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(99));
    }
}
