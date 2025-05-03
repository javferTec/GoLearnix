package com.golearnix.usecase.command;

import java.util.UUID;

public interface CompleteLessonUseCase {

  void execute(UUID courseId, UUID lessonId, UUID userId);

}
