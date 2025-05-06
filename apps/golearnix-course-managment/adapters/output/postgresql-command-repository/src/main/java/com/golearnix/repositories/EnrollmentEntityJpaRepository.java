package com.golearnix.repositories;

import com.golearnix.entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentEntityJpaRepository extends JpaRepository<EnrollmentEntity, Integer> {

}
