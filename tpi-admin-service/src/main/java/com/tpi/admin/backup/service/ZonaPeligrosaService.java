package com.tpi.admin.backup.service;

import com.tpi.admin.backup.entities.ZonaPeligrosa;
import com.tpi.admin.backup.repositories.ZonaPeligrosaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaPeligrosaService {

    @Autowired
    private ZonaPeligrosaRepository zonaPeligrosaRepository;

    public List<ZonaPeligrosa> obtenerTodas() {
        return zonaPeligrosaRepository.findAll();
    }

    public Optional<ZonaPeligrosa> obtenerPorId(Integer id) {
        return zonaPeligrosaRepository.findById(id);
    }

    public ZonaPeligrosa crear(ZonaPeligrosa zonaPeligrosa) {
        return zonaPeligrosaRepository.save(zonaPeligrosa);
    }

    public Optional<ZonaPeligrosa> actualizar(Integer id, ZonaPeligrosa nuevaZona) {
        return zonaPeligrosaRepository.findById(id).map(zonaExistente -> {
            zonaExistente.setNombre(nuevaZona.getNombre());
            zonaExistente.setLatNoroeste(nuevaZona.getLatNoroeste());
            zonaExistente.setLonNoroeste(nuevaZona.getLonNoroeste());
            zonaExistente.setLatSureste(nuevaZona.getLatSureste());
            zonaExistente.setLonSureste(nuevaZona.getLonSureste());
            return zonaPeligrosaRepository.save(zonaExistente);
        });
    }

    public boolean eliminar(Integer id) {
        if (zonaPeligrosaRepository.existsById(id)) {
            zonaPeligrosaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
