package com.tpi.admin.backup.repositories;

import com.tpi.admin.backup.entities.ZonaPeligrosa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaPeligrosaRepository extends JpaRepository<ZonaPeligrosa, Integer> {
}
