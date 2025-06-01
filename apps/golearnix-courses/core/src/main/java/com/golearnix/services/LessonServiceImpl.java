package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Lesson;
import com.golearnix.ports.inbound.LessonService;
import com.golearnix.ports.outbound.query.LessonQueryRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

  private final LessonQueryRepository lessonQueryRepositoryPort;

  @Override
  public Lesson getById(Integer id) throws ResourceNotFoundException {
    return lessonQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + id));
  }

}
