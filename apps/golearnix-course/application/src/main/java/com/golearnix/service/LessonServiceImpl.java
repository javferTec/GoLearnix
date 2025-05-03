package com.golearnix.service;

import com.golearnix.common.annotation.DomainService;
import com.golearnix.model.Lesson;
import com.golearnix.repository.LessonRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

  private final LessonRepository lessonRepository;

  @Override
  public Lesson getById(UUID id) {
    return null;
  }
}
