package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.ProgressReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ProgressReadRepository extends RedisDocumentRepository<ProgressReadModel, Integer> {

}
