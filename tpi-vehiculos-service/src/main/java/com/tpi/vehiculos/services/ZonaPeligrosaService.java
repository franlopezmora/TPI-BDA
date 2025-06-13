package com.tpi.vehiculos.services;

import com.tpi.vehiculos.dtos.ZonaPeligrosaDTO;
import com.tpi.vehiculos.entities.ZonaPeligrosa;
import com.tpi.vehiculos.repositories.ZonaPeligrosaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaPeligrosaService {

    private final ZonaPeligrosaRepository zonaPeligrosaRepository;

    public ZonaPeligrosaService(ZonaPeligrosaRepository zonaPeligrosaRepository) {
        this.zonaPeligrosaRepository = zonaPeligrosaRepository;
    }

    public List<ZonaPeligrosa> listar() {
        return zonaPeligrosaRepository.findAll();
    }

    public Optional<ZonaPeligrosa> obtenerPorId(Long id) {
        return zonaPeligrosaRepository.findById(id);
    }

    public ZonaPeligrosa crear(ZonaPeligrosa zona) {
        return zonaPeligrosaRepository.save(zona);
    }

    public Optional<ZonaPeligrosa> actualizar(Long id, ZonaPeligrosa zona) {
        return zonaPeligrosaRepository.findById(id)
                .map(z -> {
                    z.setNombre(zona.getNombre());
                    z.setLatNoroeste(zona.getLatNoroeste());
                    z.setLonNoroeste(zona.getLonNoroeste());
                    z.setLatSureste(zona.getLatSureste());
                    z.setLonSureste(zona.getLonSureste());
                    return zonaPeligrosaRepository.save(z);
                });
    }

    public boolean eliminar(Long id) {
        if (zonaPeligrosaRepository.existsById(id)) {
            zonaPeligrosaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ZonaPeligrosa> buscarPorNombreExacto(String nombre) {
        return zonaPeligrosaRepository.findByNombre(nombre);
    }

    public List<ZonaPeligrosa> buscarPorNombreParcial(String nombre) {
        return zonaPeligrosaRepository.buscarPorNombreParcial(nombre);
    }

    public List<ZonaPeligrosa> buscarZonasQueContienen(double lat, double lon) {
        return zonaPeligrosaRepository.buscarZonasQueContienen(lat, lon);
    }

    public ZonaPeligrosaDTO toDTO(ZonaPeligrosa zona) {
        return new ZonaPeligrosaDTO(
                zona.getId() != null ? zona.getId().longValue() : null,
                zona.getNombre(),
                zona.getLatNoroeste(),
                zona.getLonNoroeste(),
                zona.getLatSureste(),
                zona.getLonSureste()
        );
    }

    public ZonaPeligrosa fromDTO(ZonaPeligrosaDTO dto) {
        ZonaPeligrosa zona = new ZonaPeligrosa();
        zona.setId(dto.getId() != null ? dto.getId().intValue() : null);
        zona.setNombre(dto.getNombre());
        zona.setLatNoroeste(dto.getLatNoroeste());
        zona.setLonNoroeste(dto.getLonNoroeste());
        zona.setLatSureste(dto.getLatSureste());
        zona.setLonSureste(dto.getLonSureste());
        return zona;
    }


    public List<ZonaPeligrosaDTO> listarDTO() {
        return zonaPeligrosaRepository.findAll().stream().map(this::toDTO).toList();
    }

    public List<ZonaPeligrosaDTO> buscarPorNombreDTO(String nombre) {
        return zonaPeligrosaRepository.findByNombre(nombre).stream().map(this::toDTO).toList();
    }

    public List<ZonaPeligrosaDTO> buscarPorNombreParcialDTO(String nombre) {
        return zonaPeligrosaRepository.buscarPorNombreParcial(nombre).stream().map(this::toDTO).toList();
    }

    public List<ZonaPeligrosaDTO> buscarZonasQueContienenDTO(double lat, double lon) {
        return zonaPeligrosaRepository.buscarZonasQueContienen(lat, lon).stream().map(this::toDTO).toList();
    }

}
