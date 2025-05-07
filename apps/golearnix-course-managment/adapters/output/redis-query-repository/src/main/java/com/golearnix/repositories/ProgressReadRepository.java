package com.golearnix.repositories;

import com.golearnix.entities.ProgressReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProgressReadRepository extends RedisDocumentRepository<ProgressReadModel, Integer> {

}
