package com.golearnix.infra.jpa;

import com.golearnix.infra.driven.command.CourseCommandPort;
import com.golearnix.model.Course;

import java.util.UUID;

public class CourseCommandAdapter implements CourseCommandPort {

  @Override
  public Course save(Course course) {
    return null;
  }

  @Override
  public Course delete(UUID id) {
    return null;
  }

  @Override
  public Course enroll(Course course) {
    return null;
  }

  @Override
  public Course completeLesson(Course course) {
    return null;
  }

  @Override
  public Boolean exists(UUID id) {
    return null;
  }
}
