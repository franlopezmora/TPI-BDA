package com.tpi.pruebas.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tpi.pruebas.dtos.InteresadoDTO;
import com.tpi.pruebas.dtos.EmpleadoDTO;
import com.tpi.pruebas.dtos.PruebaDTO;
import com.tpi.pruebas.dtos.VehiculoDTO;
import com.tpi.pruebas.services.PruebaService;
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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class PruebaControllerTest {

    @Mock(lenient = true)
    PruebaService pruebaService;

    @InjectMocks
    PruebaController controller;

    private MockMvc mvc;
    private ObjectMapper mapper;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MappingJackson2HttpMessageConverter converter =
                new MappingJackson2HttpMessageConverter(mapper);
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(converter)
                .build();
    }

    @Test
    @DisplayName("GET /pruebas → lista de PruebaDTO")
    void listarPruebas() throws Exception {
        PruebaDTO dto = new PruebaDTO();
        dto.setId(5L);
        dto.setFechaHoraInicio(LocalDateTime.of(2025, 6, 18, 9, 0));
        dto.setComentario("OK");
        given(pruebaService.listar()).willReturn(List.of(dto));

        mvc.perform(get("/pruebas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(5))
                .andExpect(jsonPath("$[0].comentario").value("OK"));
    }

    @Test
    @DisplayName("GET /pruebas/{id} → existe")
    void obtenerPorId_found() throws Exception {
        PruebaDTO dto = new PruebaDTO();
        dto.setId(7L);
        given(pruebaService.obtenerPorId(7L)).willReturn(Optional.of(dto));

        mvc.perform(get("/pruebas/{id}", 7L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(7));
    }

    @Test
    @DisplayName("GET /pruebas/{id} → no existe")
    void obtenerPorId_notFound() throws Exception {
        given(pruebaService.obtenerPorId(anyLong())).willReturn(Optional.empty());

        mvc.perform(get("/pruebas/{id}", 99L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /pruebas → crea OK")
    void crearPrueba_ok() throws Exception {
        PruebaDTO input = new PruebaDTO();
        input.setFechaHoraInicio(LocalDateTime.now());
        input.setComentario("c");

        EmpleadoDTO emp = new EmpleadoDTO();
        emp.setLegajo(1L);
        input.setEmpleado(emp);

        InteresadoDTO inter = new InteresadoDTO();
        inter.setId(1L);
        input.setInteresado(inter);

        VehiculoDTO veh = new VehiculoDTO();
        veh.setId(1L);
        input.setVehiculo(veh);

        PruebaDTO saved = new PruebaDTO();
        saved.setId(10L);
        given(pruebaService.crear(any())).willReturn(saved);

        mvc.perform(post("/pruebas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    @DisplayName("DELETE /pruebas/{id} → elimina")
    void eliminarPrueba() throws Exception {
        given(pruebaService.eliminar(3L)).willReturn(true);

        mvc.perform(delete("/pruebas/{id}", 3L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /pruebas/{id} → no existe")
    void eliminarPrueba_notFound() throws Exception {
        given(pruebaService.eliminar(4L)).willReturn(false);

        mvc.perform(delete("/pruebas/{id}", 4L))
                .andExpect(status().isNotFound());
    }
}
