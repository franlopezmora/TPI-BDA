package com.tpi.pruebas.repositories;

import com.tpi.pruebas.entities.TipoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Long> {
    Optional<TipoIncidente> findByNombreIncidente(String nombreIncidente);
}
