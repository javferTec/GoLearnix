package com.golearnix.repositories;

import com.golearnix.entities.ProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressEntityJpaRepository extends JpaRepository<ProgressEntity, Integer> {

}
