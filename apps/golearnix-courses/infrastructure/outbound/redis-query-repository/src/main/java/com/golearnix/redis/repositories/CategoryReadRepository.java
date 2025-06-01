package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.CategoryReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface CategoryReadRepository extends RedisDocumentRepository<CategoryReadModel, Integer> {

}
