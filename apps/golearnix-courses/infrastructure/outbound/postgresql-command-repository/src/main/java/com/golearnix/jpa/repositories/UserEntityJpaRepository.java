package com.golearnix.jpa.repositories;

import com.golearnix.jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityJpaRepository extends JpaRepository<UserEntity, UUID> {

}
