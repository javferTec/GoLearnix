package com.golearnix.infra;

import com.golearnix.infra.driven.query.CourseQueryPort;
import com.golearnix.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CourseQueryAdapter implements CourseQueryPort {

  @Override
  public List<Course> getAll() {
    return List.of();
  }

  @Override
  public Course getById(UUID id) {
    return null;
  }
}
