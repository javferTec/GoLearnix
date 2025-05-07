package com.golearnix.repositories;

import com.golearnix.entities.ReviewReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface ReviewReadRepository extends RedisDocumentRepository<ReviewReadModel, Integer> {

}
