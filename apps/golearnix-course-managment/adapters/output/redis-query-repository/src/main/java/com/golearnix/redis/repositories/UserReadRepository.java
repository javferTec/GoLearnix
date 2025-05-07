package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.UserReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.UUID;

public interface UserReadRepository extends RedisDocumentRepository<UserReadModel, UUID> {

}
