package com.golearnix.repositories;

import com.golearnix.entities.LessonReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface LessonReadRepository extends RedisDocumentRepository<LessonReadModel, Integer> {

}
