package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.ZonaPeligrosa;
import com.tpi.vehiculos.repositories.ZonaPeligrosaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaPeligrosaService {

    private final ZonaPeligrosaRepository zonaPeligrosaRepository;

    public ZonaPeligrosaService(ZonaPeligrosaRepository zonaPeligrosaRepository) {
        this.zonaPeligrosaRepository = zonaPeligrosaRepository;
    }

    public List<ZonaPeligrosa> getAllZonas() {
        return zonaPeligrosaRepository.findAll();
    }

    public ZonaPeligrosa getZonaById(Long id) {
        return zonaPeligrosaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona peligrosa no encontrada con ID: " + id));
    }

    public ZonaPeligrosa createZona(ZonaPeligrosa zona) {
        return zonaPeligrosaRepository.save(zona);
    }

    public ZonaPeligrosa updateZona(Long id, ZonaPeligrosa zonaDetails) {
        ZonaPeligrosa zona = getZonaById(id);
        zona.setNombre(zonaDetails.getNombre());
        zona.setLatNoroeste(zonaDetails.getLatNoroeste());
        zona.setLonNoroeste(zonaDetails.getLonNoroeste());
        zona.setLatSureste(zonaDetails.getLatSureste());
        zona.setLonSureste(zonaDetails.getLonSureste());
        return zonaPeligrosaRepository.save(zona);
    }

    public void deleteZona(Long id) {
        zonaPeligrosaRepository.deleteById(id);
    }
}
