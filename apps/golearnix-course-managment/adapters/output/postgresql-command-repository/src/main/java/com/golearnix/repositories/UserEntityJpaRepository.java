package com.golearnix.repositories;

import com.golearnix.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityJpaRepository extends JpaRepository<UserEntity, UUID> {

}
