package com.tpi.admin.services;

import com.tpi.admin.entities.Vehiculo;
import com.tpi.admin.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getById(Integer id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo update(Integer id, Vehiculo vehiculo) {
        Optional<Vehiculo> optional = vehiculoRepository.findById(id);
        if (optional.isPresent()) {
            Vehiculo existente = optional.get();
            existente.setPatente(vehiculo.getPatente());
            existente.setModelo(vehiculo.getModelo());
            existente.setAnio(vehiculo.getAnio());
            return vehiculoRepository.save(existente);
        } else {
            throw new RuntimeException("Vehículo no encontrado con ID " + id);
        }
    }

    public void delete(Integer id) {
        vehiculoRepository.deleteById(id);
    }
}
