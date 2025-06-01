package com.golearnix.redis.repositories;

import com.golearnix.redis.entities.EnrollmentReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface EnrollmentReadRepository extends RedisDocumentRepository<EnrollmentReadModel, Integer> {

}
