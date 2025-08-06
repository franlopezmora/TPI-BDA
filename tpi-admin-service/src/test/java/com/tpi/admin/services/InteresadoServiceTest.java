package com.tpi.admin.services;

import com.tpi.admin.config.JacksonConfig;
import com.tpi.admin.dtos.InteresadoDTO;
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
class InteresadoServiceTest {

    @Mock
    private InteresadoRepository repo;

    @InjectMocks
    private InteresadoService service;

    private Interesado entidad;
    private InteresadoDTO dto;

    @BeforeEach
    void setUp() {
        // Creamos la entidad “cruda”
        entidad = new Interesado();
        entidad.setId(1L);
        entidad.setTipoDocumento("DNI");
        entidad.setDocumento("12345678");
        entidad.setNombre("María");
        entidad.setApellido("Gómez");
        entidad.setRestringido(true);
        entidad.setNroLicencia(5555);
        entidad.setFechaVencimientoLicencia(LocalDate.of(2025, 12, 31));

        // Y su DTO equivalente
        dto = new InteresadoDTO();
        dto.setId(entidad.getId());
        dto.setTipoDocumento(entidad.getTipoDocumento());
        dto.setDocumento(entidad.getDocumento());
        dto.setNombre(entidad.getNombre());
        dto.setApellido(entidad.getApellido());
        dto.setRestringido(entidad.getRestringido());
        dto.setNroLicencia(entidad.getNroLicencia());
        dto.setFechaVencimientoLicencia(entidad.getFechaVencimientoLicencia());
    }

    @Test
    void listarInteresados_devuelveDTOs() {
        // repo devuelve entidad
        when(repo.findAll()).thenReturn(List.of(entidad));

        // service mapea a DTO
        List<InteresadoDTO> lista = service.listarInteresados();

        assertThat(lista)
                .hasSize(1)
                .first()
                .returns(1L, InteresadoDTO::getId)
                .returns("María", InteresadoDTO::getNombre)
                .returns(true, InteresadoDTO::getRestringido);

        verify(repo).findAll();
    }

    @Test
    void crearInteresado_debeMapearYGuardar() {
        // cuando service recibe DTO, mapea a entidad y llama a repo.save(entidad)
        when(repo.save(any(Interesado.class))).thenReturn(entidad);

        InteresadoDTO creado = service.crearInteresado(dto);

        assertThat(creado.getId()).isEqualTo(1L);
        assertThat(creado.getNombre()).isEqualTo("María");
        verify(repo).save(any(Interesado.class));
    }

    @Test
    void obtenerInteresadoPorId_existente() {
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));

        Optional<InteresadoDTO> opt = service.obtenerInteresadoPorId(1L);

        assertThat(opt)
                .isPresent()
                .get()
                .returns("Gómez", InteresadoDTO::getApellido);

        verify(repo).findById(1L);
    }

    @Test
    void actualizarInteresado_existente_aplicaCambios() {
        InteresadoDTO cambios = new InteresadoDTO();
        cambios.setNombre("Ana");
        cambios.setApellido("López");
        cambios.setTipoDocumento(entidad.getTipoDocumento());
        cambios.setDocumento(entidad.getDocumento());
        cambios.setNroLicencia(entidad.getNroLicencia());
        cambios.setFechaVencimientoLicencia(entidad.getFechaVencimientoLicencia());

        when(repo.findById(1L)).thenReturn(Optional.of(entidad));
        when(repo.save(any(Interesado.class)))
                .thenAnswer(inv -> {
                    Interesado ent = inv.getArgument(0);
                    // simulamos retorno con los cambios
                    ent.setId(entidad.getId());
                    return ent;
                });

        InteresadoDTO updated = service.actualizarInteresado(1L, cambios);

        assertThat(updated.getNombre()).isEqualTo("Ana");
        assertThat(updated.getApellido()).isEqualTo("López");
        verify(repo).findById(1L);
        verify(repo).save(any(Interesado.class));
    }

    @Test
    void listarRestringidos_devuelveSoloRestringidos() {
        when(repo.findByRestringidoTrue()).thenReturn(List.of(entidad));

        List<InteresadoDTO> res = service.listarRestringidos();

        assertThat(res).allMatch(InteresadoDTO::getRestringido);
        verify(repo).findByRestringidoTrue();
    }
}
