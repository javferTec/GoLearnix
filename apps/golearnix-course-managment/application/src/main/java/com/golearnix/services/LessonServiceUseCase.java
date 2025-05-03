package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Lesson;
import com.golearnix.ports.input.LessonServicePort;
import com.golearnix.ports.output.query.LessonQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class LessonServiceUseCase implements LessonServicePort {

  private final LessonQueryRepositoryPort lessonQueryRepositoryPort;

  @Override
  public Lesson getById(Integer id) throws ResourceNotFoundException {
    return lessonQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + id));
  }

}
