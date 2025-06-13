package com.tpi.admin.services;

import com.tpi.admin.entities.Modelo;
import com.tpi.admin.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    public List<Modelo> obtenerTodos() {
        return modeloRepository.findAll();
    }

    public Optional<Modelo> obtenerPorId(Integer id) {
        return modeloRepository.findById(id);
    }

    public Modelo crear(Modelo modelo) {
        return modeloRepository.save(modelo);
    }

    public Optional<Modelo> actualizar(Integer id, Modelo modeloNuevo) {
        return modeloRepository.findById(id).map(modeloExistente -> {
            modeloExistente.setDescripcion(modeloNuevo.getDescripcion());
            modeloExistente.setMarca(modeloNuevo.getMarca());
            return modeloRepository.save(modeloExistente);
        });
    }

    public boolean eliminar(Integer id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
