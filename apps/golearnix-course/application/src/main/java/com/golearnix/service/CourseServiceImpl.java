package com.golearnix.service;

import com.golearnix.common.annotation.DomainService;
import com.golearnix.model.Course;
import com.golearnix.model.Lesson;
import com.golearnix.model.User;
import com.golearnix.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;

  @Override
  public List<Course> getAll() {
    return List.of();
  }

  @Override
  public Course getById(UUID id) {
    return null;
  }

  @Override
  public Course create(Course course) {
    return null;
  }

  @Override
  public Course update(Course source, Course target) {
    return null;
  }

  @Override
  public Course delete(UUID id) {
    return null;
  }

  @Override
  public Course enroll(Course course, User user) {
    return null;
  }

  @Override
  public Course completeLesson(Course course, Lesson lesson, User user) {
    return null;
  }
}
