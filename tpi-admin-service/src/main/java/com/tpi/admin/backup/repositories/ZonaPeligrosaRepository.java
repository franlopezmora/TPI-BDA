package com.tpi.admin.repositories;

import com.tpi.admin.entities.ZonaPeligrosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaPeligrosaRepository extends JpaRepository<ZonaPeligrosa, Integer> {
}
