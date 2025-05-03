package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.DeleteCourseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class DeleteCourseUseCaseImpl implements DeleteCourseUseCase {

  private final CourseService courseService;

  @Override
  public void execute(UUID courseId) {

  }
}
