package com.tpi.pruebas.repositories;

import com.tpi.pruebas.entities.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    List<Incidente> findByIdPrueba(Long idPrueba);
}
