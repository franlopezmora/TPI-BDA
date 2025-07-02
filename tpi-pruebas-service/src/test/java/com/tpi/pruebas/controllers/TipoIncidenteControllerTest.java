package com.tpi.pruebas.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.pruebas.entities.TipoIncidente;
import com.tpi.pruebas.repositories.TipoIncidenteRepository;
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class TipoIncidenteControllerTest {

    @Mock(lenient = true)
    TipoIncidenteRepository repo;

    @InjectMocks
    TipoIncidenteController controller;

    MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(mapper))
                .build();
    }

    @Test
    @DisplayName("GET /tipos-incidente → lista")
    void listarTipos() throws Exception {
        TipoIncidente t = new TipoIncidente();
        t.setId(2L);
        t.setNombreIncidente("Falla");
        given(repo.findAll()).willReturn(List.of(t));

        mvc.perform(get("/tipos-incidente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreIncidente").value("Falla"));
    }

    @Test
    @DisplayName("POST /tipos-incidente → crea")
    void crearTipo() throws Exception {
        TipoIncidente in = new TipoIncidente();
        in.setNombreIncidente("Nuevo");
        TipoIncidente saved = new TipoIncidente();
        saved.setId(9L);
        saved.setNombreIncidente("Nuevo");
        given(repo.save(any(TipoIncidente.class))).willReturn(saved);

        mvc.perform(post("/tipos-incidente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(in)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(9))
                .andExpect(jsonPath("$.nombreIncidente").value("Nuevo"));
    }
}

