package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.service.CourseService;
import com.golearnix.service.LessonService;
import com.golearnix.service.UserService;
import com.golearnix.usecase.CompleteLessonUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CompleteLessonUseCaseImpl implements CompleteLessonUseCase {

  private final CourseService courseService;
  private final LessonService lessonService;
  private final UserService userService;

  @Override
  public void execute(UUID courseId, UUID lessonId, UUID userId) {

  }

}
