package com.golearnix.infra.driven.query;

import com.golearnix.model.Course;

import java.util.List;
import java.util.UUID;

public interface CourseQueryPort {

  List<Course> getAll();
  Course getById(UUID id);

}
