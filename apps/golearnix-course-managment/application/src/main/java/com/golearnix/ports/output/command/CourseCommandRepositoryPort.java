package com.golearnix.ports.output.command;

import com.golearnix.domain.Course;

public interface CourseCommandRepositoryPort {

  void save(Course course);
  void delete(Integer id);
  void enroll(Course course);
  void completeLesson(Course course);

}
