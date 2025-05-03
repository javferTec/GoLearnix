package com.golearnix.infra;

import com.golearnix.infra.driven.command.CourseCommandPort;
import com.golearnix.infra.driven.query.CourseQueryPort;
import com.golearnix.model.Course;
import com.golearnix.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseRepositoryImpl implements CourseRepository {

  private final CourseQueryPort courseQueryPort;
  private final CourseCommandPort courseCommandPort;

  @Override
  public List<Course> getAll() {
    return List.of();
  }

  @Override
  public Course getById(UUID id) {
    return null;
  }

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
