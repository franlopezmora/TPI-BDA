package com.tpi.admin.services;

import com.tpi.admin.dtos.EmpleadoDTO;
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
        // Creamos la entidad, que es lo que mockea el repo
        e1 = new Empleado();
        e1.setLegajo(1L);
        e1.setNombre("Juan");
        e1.setApellido("Pérez");
        e1.setTelefonoContacto("1234-5678");
    }

    @Test
    void listarEmpleados_devuelveTodosComoDTO() {
        // El repo devuelve entidades...
        when(repo.findAll()).thenReturn(List.of(e1));

        // ...y el servicio devuelve DTOs
        List<EmpleadoDTO> todosDto = service.listarEmpleados();

        assertThat(todosDto)
                .hasSize(1)
                .allSatisfy(dto -> {
                    assertThat(dto.getLegajo()).isEqualTo(e1.getLegajo());
                    assertThat(dto.getNombre()).isEqualTo(e1.getNombre());
                    assertThat(dto.getApellido()).isEqualTo(e1.getApellido());
                    assertThat(dto.getTelefonoContacto()).isEqualTo(e1.getTelefonoContacto());
                });

        verify(repo).findAll();
    }

    @Test
    void guardarEmpleado_entityALaRepo_devuelveDTO() {
        // Cuando guardo la entidad, el repo me la devuelve
        when(repo.save(any(Empleado.class))).thenReturn(e1);


        // El servicio recibe un DTO:
        EmpleadoDTO inputDto = new EmpleadoDTO();
        inputDto.setLegajo(e1.getLegajo());
        inputDto.setNombre(e1.getNombre());
        inputDto.setApellido(e1.getApellido());
        inputDto.setTelefonoContacto(e1.getTelefonoContacto());

        // Y me devuelve un DTO con los mismos valores
        EmpleadoDTO savedDto = service.guardarEmpleado(inputDto);

        assertThat(savedDto.getLegajo()).isEqualTo(e1.getLegajo());
        assertThat(savedDto.getNombre()).isEqualTo(e1.getNombre());
        assertThat(savedDto.getApellido()).isEqualTo(e1.getApellido());
        assertThat(savedDto.getTelefonoContacto()).isEqualTo(e1.getTelefonoContacto());

        // Verifico que el repo haya recibido la entidad correcta
        ArgumentCaptor<Empleado> captor = ArgumentCaptor.forClass(Empleado.class);
        verify(repo).save(captor.capture());
        Empleado entidadPasada = captor.getValue();
        assertThat(entidadPasada.getLegajo()).isEqualTo(inputDto.getLegajo());
    }

    @Test
    void obtenerEmpleadoPorId_existente_devuelveOptionalDTO() {
        when(repo.findById(1L)).thenReturn(Optional.of(e1));

        Optional<EmpleadoDTO> optDto = service.obtenerEmpleadoPorId(1L);

        assertThat(optDto).isPresent();
        EmpleadoDTO dto = optDto.get();
        assertThat(dto.getLegajo()).isEqualTo(e1.getLegajo());

        verify(repo).findById(1L);
    }

    @Test
    void obtenerEmpleadoPorId_inexistente_devuelveEmpty() {
        when(repo.findById(2L)).thenReturn(Optional.empty());

        assertThat(service.obtenerEmpleadoPorId(2L)).isEmpty();

        verify(repo).findById(2L);
    }

    @Test
    void actualizarEmpleado_existente_aplicaCambiosYDevuelveDTO() {
        Empleado cambios = new Empleado();
        cambios.setNombre("Ana");
        cambios.setApellido("García");
        cambios.setTelefonoContacto("9999-0000");

        when(repo.findById(1L)).thenReturn(Optional.of(e1));
        when(repo.save(any(Empleado.class))).thenAnswer(inv -> {
            // Simula que repo retorna la entidad modificada
            Empleado toSave = inv.getArgument(0);
            toSave.setLegajo(1L);
            return toSave;
        });

        // DTO de entrada
        EmpleadoDTO cambiosDto = new EmpleadoDTO();
        cambiosDto.setNombre("Ana");
        cambiosDto.setApellido("García");
        cambiosDto.setTelefonoContacto("9999-0000");

        EmpleadoDTO updatedDto = service.actualizarEmpleado(1L, cambiosDto);

        assertThat(updatedDto.getNombre()).isEqualTo("Ana");
        assertThat(updatedDto.getApellido()).isEqualTo("García");
        assertThat(updatedDto.getTelefonoContacto()).isEqualTo("9999-0000");

        verify(repo).findById(1L);
        verify(repo).save(any(Empleado.class));
    }

    @Test
    void actualizarEmpleado_noExistente_lanzaRuntime() {
        when(repo.findById(5L)).thenReturn(Optional.empty());

        EmpleadoDTO dummy = new EmpleadoDTO();
        assertThatThrownBy(() -> service.actualizarEmpleado(5L, dummy))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Empleado no encontrado");

        verify(repo).findById(5L);
        verify(repo, never()).save(any());
    }

    @Test
    void eliminarEmpleado_invocaDeleteById() {
        service.eliminarEmpleado(1L);
        verify(repo).deleteById(1L);
    }
}
