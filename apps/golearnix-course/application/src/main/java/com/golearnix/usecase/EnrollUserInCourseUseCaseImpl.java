package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.service.CourseService;
import com.golearnix.service.UserService;
import com.golearnix.usecase.EnrollUserInCourseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class EnrollUserInCourseUseCaseImpl implements EnrollUserInCourseUseCase {

  private final CourseService courseService;
  private final UserService userService;

  @Override
  public void execute(UUID userId, UUID courseId) {

  }
}
