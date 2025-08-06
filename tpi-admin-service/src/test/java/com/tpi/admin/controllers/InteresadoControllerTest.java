package com.tpi.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tpi.admin.dtos.InteresadoDTO;
import com.tpi.admin.services.InteresadoService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InteresadoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Mock
    private InteresadoService service;

    @InjectMocks
    private InteresadoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        var jacksonConverter = new MappingJackson2HttpMessageConverter(mapper);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(jacksonConverter)
                .build();
    }

    private InteresadoDTO buildDto() {
        InteresadoDTO dto = new InteresadoDTO();
        dto.setId(1L);
        dto.setTipoDocumento("DNI");
        dto.setDocumento("12345678");
        dto.setNombre("María");
        dto.setApellido("Gómez");
        dto.setRestringido(false);
        dto.setNroLicencia(5555);
        dto.setFechaVencimientoLicencia(LocalDate.of(2025, 12, 31));
        return dto;
    }

    @Test
    void getAll_deberiaDevolver200yListaDTO() throws Exception {
        var dto = buildDto();
        when(service.listarInteresados()).thenReturn(List.of(dto));

        mockMvc.perform(get("/interesados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].documento").value("12345678"))
                .andExpect(jsonPath("$[0].nombre").value("María"));

        verify(service).listarInteresados();
    }

    @Test
    void create_deberiaDevolver201yDTO() throws Exception {
        var dto = buildDto();
        when(service.crearInteresado(any(InteresadoDTO.class))).thenReturn(dto);

        mockMvc.perform(post("/interesados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.apellido").value("Gómez"));

        verify(service).crearInteresado(any());
    }

    @Test
    void getById_existente_deberiaDevolver200yDTO() throws Exception {
        var dto = buildDto();
        when(service.obtenerInteresadoPorId(1L)).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/interesados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(service).obtenerInteresadoPorId(1L);
    }

    @Test
    void getById_inexistente_deberiaDevolver404() throws Exception {
        when(service.obtenerInteresadoPorId(9L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/interesados/9"))
                .andExpect(status().isNotFound());

        verify(service).obtenerInteresadoPorId(9L);
    }

    @Test
    void update_existente_deberiaDevolver200yDTO() throws Exception {
        var upd = buildDto();
        upd.setTipoDocumento("LC");
        upd.setNroLicencia(2222);

        when(service.actualizarInteresado(eq(1L), any(InteresadoDTO.class)))
                .thenReturn(upd);

        mockMvc.perform(put("/interesados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoDocumento").value("LC"))
                .andExpect(jsonPath("$.nroLicencia").value(2222));

        verify(service).actualizarInteresado(eq(1L), any());
    }

    @Test
    void update_noExistente_deberiaDevolver404() throws Exception {
        var dto = buildDto();
        doThrow(new EntityNotFoundException())
                .when(service).actualizarInteresado(eq(9L), any());

        mockMvc.perform(put("/interesados/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());

        verify(service).actualizarInteresado(eq(9L), any());
    }

    @Test
    void delete_deberiaDevolver204() throws Exception {
        doNothing().when(service).eliminarInteresado(1L);

        mockMvc.perform(delete("/interesados/1"))
                .andExpect(status().isNoContent());

        verify(service).eliminarInteresado(1L);
    }

    @Test
    void restringir_deberiaDevolver204() throws Exception {
        doNothing().when(service).restringirInteresado(1L);

        mockMvc.perform(put("/interesados/restringir/1"))
                .andExpect(status().isNoContent());

        // verificamos que el service fue invocado
        verify(service).restringirInteresado(1L);
    }

    @Test
    void restringir_noExiste_deberiaDevolver404() throws Exception {
        doThrow(new EntityNotFoundException()).when(service).restringirInteresado(5L);

        mockMvc.perform(put("/interesados/restringir/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRestringidos_deberiaDevolverListaDTO() throws Exception {
        var dto = buildDto();
        dto.setRestringido(true);
        when(service.listarRestringidos()).thenReturn(List.of(dto));

        mockMvc.perform(get("/interesados/restringidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].restringido").value(true));

        verify(service).listarRestringidos();
    }
}
