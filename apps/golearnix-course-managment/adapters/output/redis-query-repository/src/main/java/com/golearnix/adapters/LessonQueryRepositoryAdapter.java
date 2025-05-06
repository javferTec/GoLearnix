package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.User;
import com.golearnix.ports.output.query.LessonQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class LessonQueryRepositoryAdapter implements LessonQueryRepositoryPort {

  @Override
  public Optional<Lesson> getById(Integer id) {
    Lesson lesson = new Lesson(1, "Lesson 1", 1, "content", List.of(new Progress(1, new User(UUID.randomUUID()), true)));
    return Optional.of(lesson);
  }

}
