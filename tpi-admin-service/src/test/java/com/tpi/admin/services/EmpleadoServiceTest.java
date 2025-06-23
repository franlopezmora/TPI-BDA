package com.tpi.admin.services;

import com.tpi.admin.entities.Empleado;
import com.tpi.admin.repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository repo;

    @InjectMocks
    private EmpleadoService service;

    private Empleado e1;

    @BeforeEach
    void setUp() {
        e1 = new Empleado();
        e1.setLegajo(1L);
        e1.setNombre("Juan");
        e1.setApellido("Pérez");
        e1.setTelefonoContacto("1234-5678");
    }

    @Test
    void listarEmpleados_devuelveTodos() {
        when(repo.findAll()).thenReturn(List.of(e1));

        List<Empleado> todos = service.listarEmpleados();

        assertThat(todos).hasSize(1).contains(e1);
        verify(repo).findAll();
    }

    @Test
    void guardarEmpleado_debeLlamarSave() {
        when(repo.save(e1)).thenReturn(e1);

        Empleado saved = service.guardarEmpleado(e1);

        assertThat(saved).isSameAs(e1);
        verify(repo).save(e1);
    }

    @Test
    void obtenerEmpleadoPorId_existente() {
        when(repo.findById(1L)).thenReturn(Optional.of(e1));

        Optional<Empleado> opt = service.obtenerEmpleadoPorId(1L);

        assertThat(opt).isPresent().contains(e1);
        verify(repo).findById(1L);
    }

    @Test
    void obtenerEmpleadoPorId_inexistente() {
        when(repo.findById(2L)).thenReturn(Optional.empty());

        Optional<Empleado> opt = service.obtenerEmpleadoPorId(2L);

        assertThat(opt).isEmpty();
        verify(repo).findById(2L);
    }

    @Test
    void actualizarEmpleado_existente_modificaYSavage() {
        Empleado cambios = new Empleado();
        cambios.setNombre("Ana");
        cambios.setApellido("García");
        cambios.setTelefonoContacto("9999-0000");

        when(repo.findById(1L)).thenReturn(Optional.of(e1));
        when(repo.save(any(Empleado.class))).thenAnswer(inv -> inv.getArgument(0));

        Empleado updated = service.actualizarEmpleado(1L, cambios);

        assertThat(updated.getNombre()).isEqualTo("Ana");
        assertThat(updated.getApellido()).isEqualTo("García");
        assertThat(updated.getTelefonoContacto()).isEqualTo("9999-0000");
        verify(repo).findById(1L);
        verify(repo).save(updated);
    }

    @Test
    void actualizarEmpleado_noExistente_lanzaRuntimeException() {
        when(repo.findById(5L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.actualizarEmpleado(5L, e1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Empleado no encontrado");
        verify(repo).findById(5L);
        verify(repo, never()).save(any());
    }

    @Test
    void eliminarEmpleado_llamaDeleteById() {
        // no lanza excepción
        service.eliminarEmpleado(1L);
        verify(repo).deleteById(1L);
    }
}
