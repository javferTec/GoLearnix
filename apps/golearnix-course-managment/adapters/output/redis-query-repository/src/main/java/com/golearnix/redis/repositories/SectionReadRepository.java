package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.SectionReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface SectionReadRepository extends RedisDocumentRepository<SectionReadModel, Integer> {

}
