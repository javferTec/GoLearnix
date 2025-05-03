package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.dto.command.CourseCommandDto;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.command.CreateCourseUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCourseUseCaseImpl implements CreateCourseUseCase {

  private final CourseService courseService;

  @Override
  public void execute(CourseCommandDto data) {

  }
}
