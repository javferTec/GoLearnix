package com.golearnix.repositories;

import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.entities.CourseReadModel;
import com.redis.om.spring.repository.RedisDocumentRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseReadRepository extends RedisDocumentRepository<CourseReadModel, Integer> {

  List<CourseGetAllProjection> findAllProjected();

}
