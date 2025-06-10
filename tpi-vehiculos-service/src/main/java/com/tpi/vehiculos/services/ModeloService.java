package com.tpi.vehiculos.services;

import com.tpi.vehiculos.entities.Marca;
import com.tpi.vehiculos.entities.Modelo;
import com.tpi.vehiculos.repositories.ModeloRepository;
import org.springframework.stereotype.Service;
import com.tpi.vehiculos.repositories.MarcaRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;

    public ModeloService(ModeloRepository modeloRepository, MarcaRepository marcaRepository) {
        this.modeloRepository = modeloRepository;
        this.marcaRepository = marcaRepository;
    }

    public List<Modelo> listar() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> obtenerPorId(Long id) {
        return modeloRepository.findById(id);
    }

    public Modelo crear(Modelo modelo) {
        if (modelo.getMarca() != null && modelo.getMarca().getId() != null) {
            Marca marca = marcaRepository.findById(modelo.getMarca().getId().longValue())
                    .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada con ID: " + modelo.getMarca().getId()));
            modelo.setMarca(marca);
        } else {
            throw new IllegalArgumentException("La marca es requerida");
        }
        return modeloRepository.save(modelo);
    }

    public Optional<Modelo> actualizar(Long id, Modelo modelo) {
        return modeloRepository.findById(id)
                .map(m -> {
                    m.setDescripcion(modelo.getDescripcion());
                    m.setMarca(modelo.getMarca());
                    return modeloRepository.save(m);
                });
    }

    public boolean eliminar(Long id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Modelo> buscarPorDescripcion(String descripcion) {
        return modeloRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }

    public List<Modelo> buscarPorMarcaId(Long idMarca) {
        return modeloRepository.findByMarcaId(idMarca);
    }


}
