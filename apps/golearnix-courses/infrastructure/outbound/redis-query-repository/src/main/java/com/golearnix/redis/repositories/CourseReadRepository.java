package com.golearnix.redis.repositories;

import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.redis.entities.CourseReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.List;

public interface CourseReadRepository extends RedisDocumentRepository<CourseReadModel, Integer> {

  List<CourseGetAllProjection> findAllBy();

}
