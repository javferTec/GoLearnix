package com.golearnix.repositories;

import com.golearnix.entities.EnrollmentReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentReadRepository extends RedisDocumentRepository<EnrollmentReadModel, Integer> {

}
