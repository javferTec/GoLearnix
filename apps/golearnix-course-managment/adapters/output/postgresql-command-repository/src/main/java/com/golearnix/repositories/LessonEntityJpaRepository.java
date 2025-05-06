package com.golearnix.repositories;

import com.golearnix.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonEntityJpaRepository extends JpaRepository<LessonEntity, Integer> {

}
