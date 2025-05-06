package com.golearnix.repositories;

import com.golearnix.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEntityJpaRepository extends JpaRepository<CourseEntity, Integer> {

}
