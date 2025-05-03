package com.golearnix.ports.output.query;

import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;

import java.util.List;
import java.util.Optional;

public interface CourseQueryRepositoryPort {

  List<CourseGetAllProjection> getAll();
  Optional<Course> getById(Integer id);

}
