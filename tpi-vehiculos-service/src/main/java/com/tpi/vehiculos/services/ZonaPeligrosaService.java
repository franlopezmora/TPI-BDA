package com.tpi.vehiculos.services;

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

}
