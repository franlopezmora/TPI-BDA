package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.repositories.ModeloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    public List<Modelo> getAllModelos() {
        return modeloRepository.findAll();
    }

    public Modelo getModeloById(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Modelo no encontrado con ID: " + id));
    }

    public Modelo createModelo(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Modelo updateModelo(Long id, Modelo modeloDetails) {
        Modelo modelo = getModeloById(id);
        modelo.setDescripcion(modeloDetails.getDescripcion());
        modelo.setMarca(modeloDetails.getMarca());
        return modeloRepository.save(modelo);
    }

    public void deleteModelo(Long id) {
        modeloRepository.deleteById(id);
    }
}
