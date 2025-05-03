package com.golearnix.repository;

import com.golearnix.model.Course;

import java.util.List;
import java.util.UUID;

public interface CourseRepository {

  // query
  List<Course> getAll();
  Course getById(UUID id);

  // command
  Course save(Course course);
  Course delete(UUID id);
  Course enroll(Course course);
  Course completeLesson(Course course);
  Boolean exists(UUID id);

}
