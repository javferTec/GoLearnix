package com.golearnix.jpa.repositories;

import com.golearnix.jpa.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEntityJpaRepository extends JpaRepository<CourseEntity, Integer> {

}
