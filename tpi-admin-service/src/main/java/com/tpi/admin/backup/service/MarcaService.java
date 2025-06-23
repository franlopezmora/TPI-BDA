package com.tpi.admin.backup.service;

import com.tpi.admin.backup.entities.Marca;
import com.tpi.admin.backup.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> obtenerTodas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> obtenerPorId(Integer id) {
        return marcaRepository.findById(id);
    }

    public Marca crear(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Optional<Marca> actualizar(Integer id, Marca nuevaMarca) {
        return marcaRepository.findById(id).map(marcaExistente -> {
            marcaExistente.setNombre(nuevaMarca.getNombre());
            return marcaRepository.save(marcaExistente);
        });
    }

    public boolean eliminar(Integer id) {
        if (marcaRepository.existsById(id)) {
            marcaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
