package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;

import java.util.List;
import java.util.Optional;

public interface CourseQueryRepository {

  List<CourseGetAllProjection> getAll();
  Optional<Course> getById(Integer id);
  void save(Course course);
  void update(Course course);
  void delete(Integer id);

}
