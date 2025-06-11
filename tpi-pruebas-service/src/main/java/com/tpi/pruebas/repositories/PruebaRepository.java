package com.tpi.pruebas.repositories;

import com.tpi.pruebas.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    List<Prueba> findByVehiculoIdAndFechaHoraFinIsNull(Long vehiculoId);
}
