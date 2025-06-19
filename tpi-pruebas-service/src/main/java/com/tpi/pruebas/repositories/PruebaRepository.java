package com.tpi.pruebas.repositories;

import com.tpi.pruebas.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    Optional<Prueba> findByIdVehiculoAndFechaHoraFinIsNull(Long idVehiculo);

    List<Prueba> id(Long id);

}
