package com.golearnix.repositories;

import com.golearnix.entities.CategoryReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryReadRepository extends RedisDocumentRepository<CategoryReadModel, Integer> {

}
