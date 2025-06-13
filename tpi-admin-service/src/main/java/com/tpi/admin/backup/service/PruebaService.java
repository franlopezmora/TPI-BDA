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

        // Actualizamos los campos válidos según tu entidad
        existente.setFechaHoraInicio(actualizada.getFechaHoraInicio());
        existente.setFechaHoraFin(actualizada.getFechaHoraFin());
        existente.setComentario(actualizada.getComentario());
        existente.setEmpleado(actualizada.getEmpleado());
        existente.setInteresado(actualizada.getInteresado());
        existente.setVehiculo(actualizada.getVehiculo());

        return pruebaRepository.save(existente);
    }

    public void eliminarPrueba(Long id) {
        pruebaRepository.deleteById(id);
    }
}
