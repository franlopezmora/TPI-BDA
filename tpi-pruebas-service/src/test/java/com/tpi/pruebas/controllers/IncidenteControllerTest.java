package com.tpi.pruebas.controllers;

import com.tpi.pruebas.dtos.IncidenteDTO;
import com.tpi.pruebas.dtos.RegistrarIncidenteDTO;
import com.tpi.pruebas.services.PruebaService;
import com.tpi.pruebas.repositories.IncidenteRepository;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.List.of;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class IncidenteControllerTest {

    @Mock(lenient = true)
    PruebaService pruebaService;

    @Mock
    IncidenteRepository incidenteRepository; // aunque no lo uses directamente en GET

    @InjectMocks
    IncidenteController controller;

    MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        // configurar el ObjectMapper si necesitas date formats
    }

    @Test
    @DisplayName("GET /incidentes → lista de DTOs")
    void listarIncidentes() throws Exception {
        IncidenteDTO dto = new IncidenteDTO();
        dto.setId(7L);
        dto.setTipo("ERROR");
        dto.setFecha(LocalDateTime.of(2025,6,18,12,0));
        given(pruebaService.listarIncidentes()).willReturn(of(dto));

        mvc.perform(get("/incidentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(7))
                .andExpect(jsonPath("$[0].tipo").value("ERROR"));
    }

    @Test
    @DisplayName("POST /incidentes → registra correctamente")
    void registrarIncidente() throws Exception {
        RegistrarIncidenteDTO dto = new RegistrarIncidenteDTO();
        dto.setIdPrueba(1L);
        dto.setNombreTipoIncidente("Peligro");

        mvc.perform(post("/incidentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Incidente registrado correctamente"));
    }
}

