package com.tpi.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import com.tpi.admin.services.InteresadoService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InteresadoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Mock
    private InteresadoService service;

    @Mock
    private InteresadoRepository repo;

    @InjectMocks
    private InteresadoController controller;

    private Interesado i1;

    @BeforeEach
    void setUp() {
        // Inicializa mocks
        MockitoAnnotations.openMocks(this);

        // Configura ObjectMapper para Java 8 dates
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        // Añade converter para MockMvc
        MappingJackson2HttpMessageConverter jacksonConverter =
                new MappingJackson2HttpMessageConverter(mapper);

        // Construye MockMvc con nuestro controller y el converter de Jackson
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setMessageConverters(jacksonConverter)
                .build();

        // Entidad de ejemplo
        i1 = new Interesado();
        i1.setId(1L);
        i1.setTipoDocumento("DNI");
        i1.setDocumento("12345678");
        i1.setNombre("María");
        i1.setApellido("Gómez");
        i1.setRestringido(false);
        i1.setNroLicencia(5555);
        i1.setFechaVencimientoLicencia(LocalDate.of(2025, 12, 31));
    }

    @Test
    void getAll_deberiaDevolver200yLista() throws Exception {
        when(service.listarInteresados()).thenReturn(List.of(i1));

        mockMvc.perform(get("/interesados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].documento").value("12345678"))
                .andExpect(jsonPath("$[0].nombre").value("María"));
    }

    @Test
    void create_deberiaAceptarPOSTyDevolverObjeto() throws Exception {
        when(service.crearInteresado(any(Interesado.class))).thenReturn(i1);

        mockMvc.perform(post("/interesados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(i1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apellido").value("Gómez"));
    }

    @Test
    void getById_existente_deberiaDevolver200() throws Exception {
        when(service.obtenerInteresadoPorId(1L)).thenReturn(Optional.of(i1));

        mockMvc.perform(get("/interesados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getById_inexistente_deberiaDevolver404() throws Exception {
        when(service.obtenerInteresadoPorId(9L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/interesados/9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_existente_deberiaDevolver200() throws Exception {
        Interesado upd = new Interesado();
        upd.setId(1L);
        upd.setTipoDocumento("LC");
        upd.setDocumento("87654321");
        upd.setNombre("Ana");
        upd.setApellido("López");
        upd.setRestringido(true);
        upd.setNroLicencia(2222);
        upd.setFechaVencimientoLicencia(LocalDate.of(2030,1,1));

        when(service.actualizarInteresado(eq(1L), any(Interesado.class))).thenReturn(upd);

        mockMvc.perform(put("/interesados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoDocumento").value("LC"))
                .andExpect(jsonPath("$.nroLicencia").value(2222));
    }

    @Test
    void update_noExistente_deberiaDevolver404() throws Exception {
        when(service.actualizarInteresado(eq(9L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/interesados/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(i1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_deberiaDevolver204() throws Exception {
        doNothing().when(service).eliminarInteresado(1L);

        mockMvc.perform(delete("/interesados/1"))
                .andExpect(status().isNoContent());
    }

    // ———————————————— NUEVOS TESTS ————————————————

    @Test
    void restringirInteresado_existente_deberiaSetearYSalvar() throws Exception {
        i1.setRestringido(false);
        when(repo.findById(1L)).thenReturn(Optional.of(i1));
        when(repo.save(any(Interesado.class))).thenAnswer(inv -> inv.getArgument(0));

        mockMvc.perform(put("/interesados/restringir/1"))
                .andExpect(status().isOk());

        verify(repo).save(argThat(person ->
                person.getId().equals(1L) && Boolean.TRUE.equals(person.getRestringido())
        ));
    }

    @Test
    void restringirInteresado_noExistente_deberiaDevolver404() throws Exception {
        when(repo.findById(5L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/interesados/restringir/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRestringidos_deberiaDevolverSoloTrue() throws Exception {
        i1.setRestringido(true);
        when(service.listarRestringidos()).thenReturn(List.of(i1));

        mockMvc.perform(get("/interesados/restringidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].restringido").value(true));
    }
}
