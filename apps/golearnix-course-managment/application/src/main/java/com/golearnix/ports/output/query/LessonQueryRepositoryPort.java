package com.golearnix.ports.output.query;

import com.golearnix.domain.Lesson;

import java.util.Optional;

public interface LessonQueryRepositoryPort {

  Optional<Lesson> getById(Integer id);

}
