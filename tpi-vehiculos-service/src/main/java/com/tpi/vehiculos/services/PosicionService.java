package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Posicion;
import com.tpi.vehiculos.repositories.PosicionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PosicionService {

    private final PosicionRepository posicionRepository;

    public PosicionService(PosicionRepository posicionRepository) {
        this.posicionRepository = posicionRepository;
    }

    public List<Posicion> listar() {
        return posicionRepository.findAll();
    }

    public Optional<Posicion> obtenerPorId(Long id) {
        return posicionRepository.findById(id);
    }

    public Posicion crear(Posicion posicion) {
        return posicionRepository.save(posicion);
    }

    public Optional<Posicion> actualizar(Long id, Posicion posicion) {
        return posicionRepository.findById(id)
                .map(p -> {
                    p.setVehiculo(posicion.getVehiculo());
                    p.setFechaHora(posicion.getFechaHora());
                    p.setLatitud(posicion.getLatitud());
                    p.setLongitud(posicion.getLongitud());
                    return posicionRepository.save(p);
                });
    }

    public boolean eliminar(Long id) {
        if (posicionRepository.existsById(id)) {
            posicionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Posicion> buscarPorVehiculo(Long idVehiculo) {
        return posicionRepository.findByVehiculoId(idVehiculo);
    }

    public List<Posicion> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return posicionRepository.findByFechaHoraBetween(inicio, fin);
    }

}
