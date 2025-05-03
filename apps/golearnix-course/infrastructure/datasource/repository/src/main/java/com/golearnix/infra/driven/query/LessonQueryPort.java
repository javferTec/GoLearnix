package com.golearnix.infra.driven.query;

import com.golearnix.model.Lesson;

import java.util.UUID;

public interface LessonQueryPort {

  Lesson getById(UUID id);

}
