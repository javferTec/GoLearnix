package com.golearnix.repository;

import com.golearnix.model.Lesson;

import java.util.UUID;

public interface LessonRepository {

  // query
  Lesson getById(UUID id);

}
