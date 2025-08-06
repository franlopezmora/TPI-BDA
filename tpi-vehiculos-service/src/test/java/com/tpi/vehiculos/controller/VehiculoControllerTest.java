package com.tpi.vehiculos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.vehiculos.controllers.VehiculoController;
import com.tpi.vehiculos.dtos.VehiculoDTO;
import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.services.VehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VehiculoControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private VehiculoService service;

    @InjectMocks
    private VehiculoController controller;

    @BeforeEach // se ejecuta antes de cada test para recopilar datos
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test // indica que la funcion es la que se va a testear para luego con el mvn test ejecute este metodo
    void listar_deberiaDevolver200yListaDTO() throws Exception {
        // Preparamos entidad y DTO
        Modelo modelo = new Modelo();
        modelo.setId(10);
        Vehiculo v = new Vehiculo();
        v.setId(1);
        v.setPatente("ABC123");
        v.setAnio(2020);
        v.setModelo(modelo);

        VehiculoDTO dto = new VehiculoDTO(1L, "ABC123", 2020, 10L);

        // Stub del service
        when(service.listar()).thenReturn(List.of(v));
        when(service.toDTO(v)).thenReturn(dto);

        mockMvc.perform(get("/vehiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].patente").value("ABC123"))
                .andExpect(jsonPath("$[0].anio").value(2020))
                .andExpect(jsonPath("$[0].idModelo").value(10));
    }

    @Test
    void obtenerPorId_existente_deberiaDevolverDTO() throws Exception {
        Modelo modelo = new Modelo(); modelo.setId(11);
        Vehiculo v = new Vehiculo();
        v.setId(2);
        v.setPatente("XYZ999");
        v.setAnio(2021);
        v.setModelo(modelo);

        VehiculoDTO dto = new VehiculoDTO(2L, "XYZ999", 2021, 11L);

        when(service.obtenerPorId(2L)).thenReturn(Optional.of(v));
        when(service.toDTO(v)).thenReturn(dto);

        mockMvc.perform(get("/vehiculos/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.patente").value("XYZ999"))
                .andExpect(jsonPath("$.anio").value(2021))
                .andExpect(jsonPath("$.idModelo").value(11));
    }

    @Test
    void obtenerPorId_inexistente_deberiaDevolver404() throws Exception {
        when(service.obtenerPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/vehiculos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_deberiaAceptarJSONyRetornarDTO() throws Exception {
        VehiculoDTO inputDto = new VehiculoDTO(null, "NEW000", 2022, 12L);

        // Entidad que fromDTO debería crear
        Modelo modelo = new Modelo(); modelo.setId(12);
        Vehiculo fromDto = new Vehiculo();
        fromDto.setPatente("NEW000");
        fromDto.setAnio(2022);
        fromDto.setModelo(modelo);

        // Entidad salvada con ID
        Vehiculo saved = new Vehiculo();
        saved.setId(5);
        saved.setPatente("NEW000");
        saved.setAnio(2022);
        saved.setModelo(modelo);

        VehiculoDTO savedDto = new VehiculoDTO(5L, "NEW000", 2022, 12L);

        when(service.fromDTO(argThat(d -> d.getPatente().equals("NEW000"))))
                .thenReturn(fromDto);
        when(service.crear(fromDto)).thenReturn(saved);
        when(service.toDTO(saved)).thenReturn(savedDto);

        mockMvc.perform(post("/vehiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.patente").value("NEW000"))
                .andExpect(jsonPath("$.anio").value(2022))
                .andExpect(jsonPath("$.idModelo").value(12));
    }

    @Test
    void eliminar_existente_deberiaDevolver204() throws Exception {
        when(service.eliminar(3L)).thenReturn(true);

        mockMvc.perform(delete("/vehiculos/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminar_inexistente_deberiaDevolver404() throws Exception {
        when(service.eliminar(99L)).thenReturn(false);

        mockMvc.perform(delete("/vehiculos/99"))
                .andExpect(status().isNotFound());
    }
}
