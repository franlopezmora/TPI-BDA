package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.ZonaPeligrosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaPeligrosaRepository extends JpaRepository<ZonaPeligrosa, Long> {
}
