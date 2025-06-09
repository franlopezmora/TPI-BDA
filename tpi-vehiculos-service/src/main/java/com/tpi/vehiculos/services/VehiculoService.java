package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Vehiculo;
import com.tpi.vehiculos.repositories.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) {
        return vehiculoRepository.findById(id)
                .map(v -> {
                    v.setPatente(vehiculoActualizado.getPatente());
                    v.setModelo(vehiculoActualizado.getModelo());
                    v.setAnio(vehiculoActualizado.getAnio());
                    return vehiculoRepository.save(v);
                })
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }
}
