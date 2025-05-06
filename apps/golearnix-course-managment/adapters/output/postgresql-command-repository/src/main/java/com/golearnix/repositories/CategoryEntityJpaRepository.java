package com.golearnix.repositories;

import com.golearnix.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEntityJpaRepository extends JpaRepository<CategoryEntity, Integer> {

}
