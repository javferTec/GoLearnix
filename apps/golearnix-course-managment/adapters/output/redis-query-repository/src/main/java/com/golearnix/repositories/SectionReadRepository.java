package com.golearnix.repositories;

import com.golearnix.entities.SectionReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface SectionReadRepository extends RedisDocumentRepository<SectionReadModel, Integer> {

}
