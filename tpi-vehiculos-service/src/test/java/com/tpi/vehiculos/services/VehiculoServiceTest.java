package com.tpi.vehiculos.services;

import com.tpi.vehiculos.dtos.VehiculoDTO;
import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.repositories.ModeloRepository;
import com.tpi.vehiculos.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehiculoServiceTest {

    @Mock
    private VehiculoRepository vehiculoRepository;

    @Mock
    private ModeloRepository modeloRepository;

    private VehiculoService service;

    private Vehiculo v1;
    private Modelo m1;

    @BeforeEach
    void setUp() {
        // Inicializar mocks
        MockitoAnnotations.openMocks(this);

        // Crear instancia de servicio con el repositorio que recibe por constructor
        service = new VehiculoService(vehiculoRepository);

        // Inyectar el mock de modeloRepository en el campo privado usando reflexión
        ReflectionTestUtils.setField(service, "modeloRepository", modeloRepository);

        // Datos de ejemplo
        m1 = new Modelo();
        m1.setId(10);
        m1.setDescripcion("Corolla");

        v1 = new Vehiculo();
        v1.setId(100);
        v1.setPatente("ABC123");
        v1.setAnio(2020);
        v1.setModelo(m1);
    }

    @Test
    void listar_debeRetornarTodos() {
        when(vehiculoRepository.findAll()).thenReturn(List.of(v1));

        List<Vehiculo> resultados = service.listar();

        assertThat(resultados).hasSize(1).contains(v1);
        verify(vehiculoRepository).findAll();
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaOptionalConValor() {
        when(vehiculoRepository.findById(100L)).thenReturn(Optional.of(v1));

        Optional<Vehiculo> opt = service.obtenerPorId(100L);

        assertThat(opt).isPresent().get().isEqualTo(v1);
        verify(vehiculoRepository).findById(100L);
    }

    @Test
    void crear_debeGuardarYRetornarEntidad() {
        when(vehiculoRepository.save(v1)).thenReturn(v1);

        Vehiculo creado = service.crear(v1);

        assertThat(creado).isEqualTo(v1);
        verify(vehiculoRepository).save(v1);
    }

    @Test
    void actualizar_cuandoExiste_debeModificarYGuardar() {
        Vehiculo cambios = new Vehiculo();
        cambios.setPatente("XYZ999");
        cambios.setAnio(2021);
        cambios.setModelo(m1);

        when(vehiculoRepository.findById(100L)).thenReturn(Optional.of(v1));
        when(vehiculoRepository.save(any(Vehiculo.class))).thenAnswer(inv -> inv.getArgument(0));

        Optional<Vehiculo> updated = service.actualizar(100L, cambios);

        assertThat(updated).isPresent();
        Vehiculo v = updated.get();
        assertThat(v.getPatente()).isEqualTo("XYZ999");
        assertThat(v.getAnio()).isEqualTo(2021);
        verify(vehiculoRepository).findById(100L);
        verify(vehiculoRepository).save(v);
    }

    @Test
    void eliminar_cuandoExiste_devuelveTrue() {
        when(vehiculoRepository.existsById(100L)).thenReturn(true);

        boolean ok = service.eliminar(100L);

        assertThat(ok).isTrue();
        verify(vehiculoRepository).deleteById(100L);
    }

    @Test
    void eliminar_cuandoNoExiste_devuelveFalse() {
        when(vehiculoRepository.existsById(200L)).thenReturn(false);

        boolean ok = service.eliminar(200L);

        assertThat(ok).isFalse();
        verify(vehiculoRepository, never()).deleteById(any());
    }

    @Test
    void buscarPorPatente_debeDelegarAlRepo() {
        when(vehiculoRepository.findByPatente("ABC123")).thenReturn(Optional.of(v1));

        Optional<Vehiculo> opt = service.buscarPorPatente("ABC123");

        assertThat(opt).isPresent().contains(v1);
        verify(vehiculoRepository).findByPatente("ABC123");
    }

    @Test
    void buscarPorAnio_debeDelegarAlRepo() {
        when(vehiculoRepository.findByAnio(2020)).thenReturn(List.of(v1));

        List<Vehiculo> list = service.buscarPorAnio(2020);

        assertThat(list).hasSize(1).contains(v1);
        verify(vehiculoRepository).findByAnio(2020);
    }

    @Test
    void buscarPorModeloId_debeDelegarAlRepo() {
        when(vehiculoRepository.findByModeloId(10L)).thenReturn(List.of(v1));

        List<Vehiculo> list = service.buscarPorModeloId(10L);

        assertThat(list).hasSize(1).contains(v1);
        verify(vehiculoRepository).findByModeloId(10L);
    }

    @Test
    void toDTO_y_fromDTO_mapeoBidireccional() {
        // toDTO
        VehiculoDTO dto = service.toDTO(v1);
        assertThat(dto.getId()).isEqualTo(100L);
        assertThat(dto.getPatente()).isEqualTo("ABC123");
        assertThat(dto.getAnio()).isEqualTo(2020);
        assertThat(dto.getIdModelo()).isEqualTo(10L);

        // fromDTO
        when(modeloRepository.findById(10L)).thenReturn(Optional.of(m1));
        Vehiculo v2 = service.fromDTO(dto);
        assertThat(v2.getPatente()).isEqualTo("ABC123");
        assertThat(v2.getAnio()).isEqualTo(2020);
        assertThat(v2.getModelo()).isEqualTo(m1);
    }

    @Test
    void fromDTO_cuandoModeloNoExiste_debeLanzarExcepcion() {
        VehiculoDTO dto = new VehiculoDTO(null, "ZZZ000", 1999, 99L);
        when(modeloRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.fromDTO(dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Modelo no encontrado");
    }
}
