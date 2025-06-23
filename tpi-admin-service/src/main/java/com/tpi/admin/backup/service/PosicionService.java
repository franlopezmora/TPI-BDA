package com.tpi.admin.backup.service;

import com.tpi.admin.backup.entities.Posicion;
import com.tpi.admin.backup.repositories.PosicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosicionService {

    @Autowired
    private PosicionRepository posicionRepository;

    public List<Posicion> obtenerTodas() {
        return posicionRepository.findAll();
    }

    public Optional<Posicion> obtenerPorId(Long id) {
        return posicionRepository.findById(id);
    }

    public Posicion crear(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    public Optional<Posicion> actualizar(Long id, Posicion nuevaPosicion) {
        return posicionRepository.findById(id).map(posicionExistente -> {
            posicionExistente.setVehiculo(nuevaPosicion.getVehiculo());
            posicionExistente.setFechaHora(nuevaPosicion.getFechaHora());
            posicionExistente.setLatitud(nuevaPosicion.getLatitud());
            posicionExistente.setLongitud(nuevaPosicion.getLongitud());
            return posicionRepository.save(posicionExistente);
        });
    }

    public boolean eliminar(Long id) {
        if (posicionRepository.existsById(id)) {
            posicionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
