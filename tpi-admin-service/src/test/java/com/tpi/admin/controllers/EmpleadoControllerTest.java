package com.tpi.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpi.admin.entities.Empleado;
import com.tpi.admin.services.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
public class EmpleadoControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Mock
    private EmpleadoService service;

    @InjectMocks
    private EmpleadoController controller;

    private Empleado e1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        e1 = new Empleado();
        e1.setLegajo(1L);
        e1.setNombre("Juan");
        e1.setApellido("Pérez");
        e1.setTelefonoContacto("1234-5678");
    }

    @Test
    void getAll_deberiaDevolver200yLista() throws Exception {
        when(service.listarEmpleados()).thenReturn(List.of(e1));

        mockMvc.perform(get("/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].legajo").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    void create_deberiaAceptarPOSTyDevolverObjeto() throws Exception {
        when(service.guardarEmpleado(any(Empleado.class))).thenReturn(e1);

        mockMvc.perform(post("/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(e1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.legajo").value(1))
                .andExpect(jsonPath("$.apellido").value("Pérez"));
    }

    @Test
    void getById_existente_deberiaDevolver200() throws Exception {
        when(service.obtenerEmpleadoPorId(1L)).thenReturn(Optional.of(e1));

        mockMvc.perform(get("/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void getById_inexistente_deberiaDevolver404() throws Exception {
        when(service.obtenerEmpleadoPorId(5L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/empleados/5"))
                .andExpect(status().isNotFound());
    }

    @Test
    void update_existente_deberiaDevolver200() throws Exception {
        Empleado upd = new Empleado();
        upd.setLegajo(1L);
        upd.setNombre("Ana");
        upd.setApellido("García");
        upd.setTelefonoContacto("9999-0000");

        when(service.actualizarEmpleado(eq(1L), any(Empleado.class))).thenReturn(upd);

        mockMvc.perform(put("/empleados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(upd)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Ana"))
                .andExpect(jsonPath("$.telefonoContacto").value("9999-0000"));
    }

    @Test
    void update_noExistente_deberiaDevolver404() throws Exception {
        when(service.actualizarEmpleado(eq(9L), any())).thenThrow(new RuntimeException());

        mockMvc.perform(put("/empleados/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(e1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_deberiaDevolver204() throws Exception {
        doNothing().when(service).eliminarEmpleado(1L);

        mockMvc.perform(delete("/empleados/1"))
                .andExpect(status().isNoContent());
    }
}
