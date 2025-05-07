package com.golearnix.repositories;

import com.golearnix.entities.UserReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.UUID;

public interface UserReadRepository extends RedisDocumentRepository<UserReadModel, UUID> {

}
