package com.golearnix.infra.driven.command;

import com.golearnix.model.Course;

import java.util.UUID;

public interface CourseCommandPort {

  Course save(Course course);
  Course delete(UUID id);
  Course enroll(Course course);
  Course completeLesson(Course course);
  Boolean exists(UUID id);

}
