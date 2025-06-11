package com.tpi.pruebas.services;

import com.tpi.pruebas.dtos.PruebaDTO;
import com.tpi.pruebas.entities.Prueba;
import com.tpi.pruebas.repositories.PruebaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    private final PruebaRepository pruebaRepository;

    public PruebaService(PruebaRepository pruebaRepository) {
        this.pruebaRepository = pruebaRepository;
    }

    public List<Prueba> listar() {
        return pruebaRepository.findAll();
    }

    public Optional<Prueba> obtenerPorId(Long id) {
        return pruebaRepository.findById(id);
    }

    public Prueba crear(Prueba prueba) {
        return pruebaRepository.save(prueba);
    }

    public boolean eliminar(Long id) {
        if (pruebaRepository.existsById(id)) {
            pruebaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
