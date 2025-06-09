package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.Posicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosicionRepository extends JpaRepository<Posicion, Long> {
}

