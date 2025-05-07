package com.golearnix.redis.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.ports.output.query.LessonQueryRepositoryPort;
import com.golearnix.redis.mappers.specific.LessonRedisMapper;
import com.golearnix.redis.repositories.LessonReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

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
