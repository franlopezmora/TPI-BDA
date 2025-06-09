package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Marca;
import com.tpi.vehiculos.repositories.MarcaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> getAllMarcas() {
        return marcaRepository.findAll();
    }

    public Marca getMarcaById(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Marca no encontrada con ID: " + id));
    }

    public Marca createMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca updateMarca(Long id, Marca marcaDetails) {
        Marca marca = getMarcaById(id);
        marca.setNombre(marcaDetails.getNombre());
        return marcaRepository.save(marca);
    }

    public void deleteMarca(Long id) {
        marcaRepository.deleteById(id);
    }
}
