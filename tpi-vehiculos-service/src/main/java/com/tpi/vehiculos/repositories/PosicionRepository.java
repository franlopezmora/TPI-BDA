package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Long> {
    List<Posicion> findByVehiculoId(Long idVehiculo);

    @Query("SELECT p FROM Posicion p WHERE p.fechaHora BETWEEN :inicio AND :fin")
    List<Posicion> findByFechaHoraBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}

