package com.tpi.admin.services;

import com.tpi.admin.config.JacksonConfig;
import com.tpi.admin.entities.Interesado;
import com.tpi.admin.repositories.InteresadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Import(JacksonConfig.class)
class InteresadoServiceTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private InteresadoRepository repo;


    @InjectMocks
    private InteresadoService service;

    private Interesado i1;

    @BeforeEach
    void setUp() {
        i1 = new Interesado();
        i1.setId(1L);
        i1.setTipoDocumento("DNI");
        i1.setDocumento("12345678");
        i1.setNombre("María");
        i1.setApellido("Gómez");
        i1.setRestringido(true);
        i1.setNroLicencia(5555);
        i1.setFechaVencimientoLicencia(LocalDate.of(2025, 12, 31));
    }

    @Test
    void listarInteresados_devuelveTodos() {
        when(repo.findAll()).thenReturn(List.of(i1));

        List<Interesado> todos = service.listarInteresados();

        assertThat(todos).hasSize(1).contains(i1);
        verify(repo).findAll();
    }

    @Test
    void crearInteresado_debeLlamarSave() {
        when(repo.save(i1)).thenReturn(i1);

        Interesado saved = service.crearInteresado(i1);

        assertThat(saved).isSameAs(i1);
        verify(repo).save(i1);
    }

    @Test
    void obtenerInteresadoPorId_existente() {
        when(repo.findById(1L)).thenReturn(Optional.of(i1));

        Optional<Interesado> opt = service.obtenerInteresadoPorId(1L);

        assertThat(opt).isPresent().contains(i1);
        verify(repo).findById(1L);
    }

    @Test
    void obtenerInteresadoPorId_inexistente() {
        when(repo.findById(9L)).thenReturn(Optional.empty());

        Optional<Interesado> opt = service.obtenerInteresadoPorId(9L);

        assertThat(opt).isEmpty();
        verify(repo).findById(9L);
    }

    @Test
    void actualizarInteresado_existente_aplicaCambios() {
        Interesado cambios = new Interesado();
        cambios.setTipoDocumento("LC");
        cambios.setDocumento("87654321");
        cambios.setNombre("Ana");
        cambios.setApellido("López");
        cambios.setRestringido(false);
        cambios.setNroLicencia(1111);
        cambios.setFechaVencimientoLicencia(LocalDate.of(2030, 1, 1));

        when(repo.findById(1L)).thenReturn(Optional.of(i1));
        when(repo.save(any(Interesado.class))).thenAnswer(inv -> inv.getArgument(0));

        Interesado updated = service.actualizarInteresado(1L, cambios);

        assertThat(updated.getTipoDocumento()).isEqualTo("LC");
        assertThat(updated.getDocumento()).isEqualTo("87654321");
        assertThat(updated.getNombre()).isEqualTo("Ana");
        assertThat(updated.getApellido()).isEqualTo("López");
        assertThat(updated.getRestringido()).isFalse();
        assertThat(updated.getNroLicencia()).isEqualTo(1111);
        assertThat(updated.getFechaVencimientoLicencia())
                .isEqualTo(LocalDate.of(2030, 1, 1));

        verify(repo).findById(1L);
        verify(repo).save(updated);
    }

    @Test
    void actualizarInteresado_noExistente_lanzaRuntimeException() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.actualizarInteresado(99L, i1))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Interesado no encontrado");
        verify(repo).findById(99L);
        verify(repo, never()).save(any());
    }

    @Test
    void eliminarInteresado_llamaDeleteById() {
        service.eliminarInteresado(1L);
        verify(repo).deleteById(1L);
    }

    @Test
    void listarRestringidos_devuelveSoloTrue() {
        when(repo.findByRestringidoTrue()).thenReturn(List.of(i1));

        List<Interesado> restringidos = service.listarRestringidos();

        assertThat(restringidos).hasSize(1).allMatch(Interesado::getRestringido);
        verify(repo).findByRestringidoTrue();
    }
}
