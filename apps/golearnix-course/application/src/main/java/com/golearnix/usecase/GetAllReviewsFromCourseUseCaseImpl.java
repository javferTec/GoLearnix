package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.query.GetAllReviewsFromCourseUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetAllReviewsFromCourseUseCaseImpl implements GetAllReviewsFromCourseUseCase {

  private final CourseService courseService;

  @Override
  public GetAllReviewsFromCourseUseCase execute(UUID courseId) {
    return null;
  }
}
