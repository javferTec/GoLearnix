package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.dto.query.getAllCourses.GetAllCoursesQueryDto;
import com.golearnix.service.CourseService;
import com.golearnix.usecase.GetAllCoursesUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllCoursesUseCaseImpl implements GetAllCoursesUseCase {

  private final CourseService courseService;

  @Override
  public List<GetAllCoursesQueryDto> execute() {
    return List.of();
  }
}
