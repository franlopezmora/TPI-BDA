package com.tpi.reportes.repositories;



import com.tpi.reportes.entities.Prueba;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PruebaRepository extends JpaRepository<Prueba, Long> {
    List<Prueba> findByVehiculoId(Integer vehiculoId);
}