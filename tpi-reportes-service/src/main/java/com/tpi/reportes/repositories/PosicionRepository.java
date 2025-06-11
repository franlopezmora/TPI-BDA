package com.tpi.reportes.repositories;

import com.tpi.reportes.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PosicionRepository extends JpaRepository<Posicion, Long> {
    List<Posicion> findByVehiculoIdAndFechaHoraBetween(
            Integer vehiculoId,
            LocalDateTime desde,
            LocalDateTime hasta
    );
}