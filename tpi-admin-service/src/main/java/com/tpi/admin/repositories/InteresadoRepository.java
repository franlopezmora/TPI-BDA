package com.tpi.admin.repositories;

import com.tpi.admin.entities.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado, Long> {
}