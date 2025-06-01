package com.golearnix.ports.outbound.command;

import com.golearnix.domain.Course;

public interface CourseCommandRepository {

  void save(Course course);
  void delete(Integer id);

}
