package com.golearnix.usecase.query;

import java.util.UUID;

public interface GetAllReviewsFromCourseUseCase {

  GetAllReviewsFromCourseUseCase execute(UUID courseId);

}
