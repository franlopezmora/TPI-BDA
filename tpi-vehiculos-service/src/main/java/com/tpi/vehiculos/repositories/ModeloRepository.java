package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByDescripcionContainingIgnoreCase(String descripcion);

    List<Modelo> findByMarcaId(Long idMarca);
}
