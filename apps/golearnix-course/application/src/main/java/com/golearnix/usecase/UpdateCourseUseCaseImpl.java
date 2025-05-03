package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.dto.command.CourseCommandDto;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.UpdateCourseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateCourseUseCaseImpl implements UpdateCourseUseCase {

  private final CourseService courseService;

  @Override
  public void execute(UUID courseId, CourseCommandDto data) {

  }
}
