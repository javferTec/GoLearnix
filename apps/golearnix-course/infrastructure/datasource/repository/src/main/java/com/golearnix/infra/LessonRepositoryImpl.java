package com.golearnix.infra;

import com.golearnix.infra.driven.query.LessonQueryPort;
import com.golearnix.model.Lesson;
import com.golearnix.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LessonRepositoryImpl implements LessonRepository {

  private final LessonQueryPort lessonQueryPort;

  @Override
  public Lesson getById(UUID id) {
    return null;
  }
}
