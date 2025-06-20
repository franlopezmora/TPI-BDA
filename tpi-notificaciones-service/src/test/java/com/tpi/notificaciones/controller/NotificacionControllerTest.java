package com.tpi.notificaciones.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tpi.notificaciones.controllers.NotificacionController;
import com.tpi.notificaciones.dtos.NotificacionDTO;
import com.tpi.notificaciones.services.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NotificacionControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Mock
    private NotificacionService service;

    @BeforeEach
    void setup() {
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        NotificacionController controller = new NotificacionController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void enviarNotificacion_invocaServicioYDevuelveOk() throws Exception {
        NotificacionDTO dto = new NotificacionDTO(
                "Hola mundo",
                "INFO",
                LocalDateTime.of(2025, 6, 18, 15, 30)
        );
        String json = mapper.writeValueAsString(dto);

        mockMvc.perform(post("/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Notificación enviada a Discord"));

        verify(service).enviarNotificacion(argThat(
                n -> n.getMensaje().equals(dto.getMensaje()) &&
                        n.getTipo().equals(dto.getTipo()) &&
                        n.getFecha().equals(dto.getFecha())
        ));
    }
}
