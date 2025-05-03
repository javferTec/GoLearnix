package com.golearnix.service;

import com.golearnix.common.exception.ResourceNotFoundException;
import com.golearnix.model.Lesson;

import java.util.UUID;

public interface LessonService {

  // query
  Lesson getById(UUID id) throws ResourceNotFoundException;

}
