package com.golearnix.usecase.command;

import java.util.UUID;

public interface EnrollUserInCourseUseCase {

  void execute(UUID userId, UUID courseId);

}
