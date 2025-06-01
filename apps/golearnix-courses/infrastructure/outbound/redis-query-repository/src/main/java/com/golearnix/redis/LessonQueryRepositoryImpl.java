package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.ports.outbound.query.LessonQueryRepository;
import com.golearnix.redis.mappers.specific.LessonRedisMapper;
import com.golearnix.redis.repositories.LessonReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class LessonQueryRepositoryImpl implements LessonQueryRepository {

  private final LessonReadRepository lessonReadRepository;
  private final LessonRedisMapper lessonRedisMapper;

  @Override
  public Optional<Lesson> getById(Integer id) {
    return lessonReadRepository.findById(id)
        .map(lessonRedisMapper::toDomain);
  }

}
