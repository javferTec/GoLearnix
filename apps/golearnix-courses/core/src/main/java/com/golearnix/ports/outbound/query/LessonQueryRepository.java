package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Lesson;

import java.util.Optional;

public interface LessonQueryRepository {

  Optional<Lesson> getById(Integer id);

}
