package com.golearnix.jpa.repositories;

import com.golearnix.jpa.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonEntityJpaRepository extends JpaRepository<LessonEntity, Integer> {

}
