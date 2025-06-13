package com.tpi.reportes.repositories;

import com.tpi.reportes.entities.TipoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoIncidenteRepository extends JpaRepository<TipoIncidente, Long> {
    Optional<TipoIncidente> findByNombre(String nombre);
}