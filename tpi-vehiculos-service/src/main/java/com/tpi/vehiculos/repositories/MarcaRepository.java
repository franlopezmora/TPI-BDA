package com.tpi.vehiculos.repositories;

import com.tpi.vehiculos.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNombre(String nombre);

    List<Marca> findByNombreContainingIgnoreCase(String nombre);

}