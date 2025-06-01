package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.ReviewReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface ReviewReadRepository extends RedisDocumentRepository<ReviewReadModel, Integer> {

}
