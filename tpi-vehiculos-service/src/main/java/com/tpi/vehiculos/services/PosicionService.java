package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.repositories.PosicionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosicionService {

    private final PosicionRepository posicionRepository;

    public PosicionService(PosicionRepository posicionRepository) {
        this.posicionRepository = posicionRepository;
    }

    public List<Posicion> getAllPosiciones() {
        return posicionRepository.findAll();
    }

    public Posicion getPosicionById(Long id) {
        return posicionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Posición no encontrada con ID: " + id));
    }

    public Posicion createPosicion(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    public Posicion updatePosicion(Long id, Posicion posicionDetails) {
        Posicion posicion = getPosicionById(id);
        posicion.setFechaHora(posicionDetails.getFechaHora());
        posicion.setLatitud(posicionDetails.getLatitud());
        posicion.setLongitud(posicionDetails.getLongitud());
        posicion.setVehiculo(posicionDetails.getVehiculo());
        return posicionRepository.save(posicion);
    }

    public void deletePosicion(Long id) {
        posicionRepository.deleteById(id);
    }
}
