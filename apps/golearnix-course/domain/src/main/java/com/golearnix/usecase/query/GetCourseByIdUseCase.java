package com.golearnix.usecase.query;

import com.golearnix.dto.query.GetCourseByIdQueryDto;

import java.util.UUID;

public interface GetCourseByIdUseCase {

  GetCourseByIdQueryDto execute(UUID courseId);

}
