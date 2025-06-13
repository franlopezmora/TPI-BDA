package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.ZonaPeligrosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZonaPeligrosaRepository extends JpaRepository<ZonaPeligrosa, Long> {
    List<ZonaPeligrosa> findByNombre(String nombre);

    @Query("SELECT z FROM ZonaPeligrosa z WHERE LOWER(z.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<ZonaPeligrosa> buscarPorNombreParcial(@Param("nombre") String nombre);

    @Query("SELECT z FROM ZonaPeligrosa z " +
            "WHERE :lat BETWEEN z.latSureste AND z.latNoroeste " +
            "AND :lon BETWEEN z.lonNoroeste AND z.lonSureste")
    List<ZonaPeligrosa> buscarZonasQueContienen(@Param("lat") double lat, @Param("lon") double lon);

}
