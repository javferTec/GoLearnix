package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.User;
import com.golearnix.mappers.specific.LessonRedisMapper;
import com.golearnix.ports.output.query.LessonQueryRepositoryPort;
import com.golearnix.repositories.LessonReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class LessonQueryRepositoryAdapter implements LessonQueryRepositoryPort {

  private final LessonReadRepository lessonReadRepository;
  private final LessonRedisMapper lessonRedisMapper;

  @Override
  public Optional<Lesson> getById(Integer id) {
    return lessonReadRepository.findById(id)
        .map(lessonRedisMapper::toDomain);
  }

}
