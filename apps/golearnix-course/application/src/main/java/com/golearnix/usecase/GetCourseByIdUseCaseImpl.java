package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.dto.query.GetCourseByIdQueryDto;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.GetCourseByIdUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetCourseByIdUseCaseImpl implements GetCourseByIdUseCase {

  private final CourseService courseService;

  @Override
  public GetCourseByIdQueryDto execute(UUID courseId) {
    return null;
  }
}
