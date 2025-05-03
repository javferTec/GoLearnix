package com.golearnix.infra;

import com.golearnix.infra.driven.query.LessonQueryPort;
import com.golearnix.model.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LessonQueryAdapter implements LessonQueryPort {

  @Override
  public Lesson getById(UUID id) {
    return null;
  }
}
