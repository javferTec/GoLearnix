package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.LessonReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface LessonReadRepository extends RedisDocumentRepository<LessonReadModel, Integer> {

}
