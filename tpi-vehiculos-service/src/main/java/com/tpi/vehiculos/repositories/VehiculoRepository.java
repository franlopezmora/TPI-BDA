package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByPatente(String patente);

    List<Vehiculo> findByAnio(Integer anio);

    List<Vehiculo> findByModeloId(Long idModelo);
}
