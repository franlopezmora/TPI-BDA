package com.tpi.admin.services;

import com.tpi.admin.entities.Prueba;
import com.tpi.admin.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    public List<Prueba> listarPruebas() {
        return pruebaRepository.findAll();
    }

    public Prueba crearPrueba(Prueba prueba) {
        return pruebaRepository.save(prueba);
    }

    public Optional<Prueba> obtenerPruebaPorId(Long id) {
        return pruebaRepository.findById(id);
    }

    public Prueba actualizarPrueba(Long id, Prueba actualizada) {
        Prueba existente = pruebaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prueba no encontrada con ID: " + id));

        // Suponiendo algunos campos (ajustá según tu entidad real)
        existente.setFecha(actualizada.getFecha());
        existente.setResultado(actualizada.getResultado());
        existente.setEmpleado(actualizada.getEmpleado());
        existente.setInteresado(actualizada.getInteresado());

        return pruebaRepository.save(existente);
    }

    public void eliminarPrueba(Long id) {
        pruebaRepository.deleteById(id);
    }
}
