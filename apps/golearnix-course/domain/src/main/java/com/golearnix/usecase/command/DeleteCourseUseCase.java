package com.golearnix.usecase.command;

import java.util.UUID;

public interface DeleteCourseUseCase {

  void execute(UUID courseId);

}
