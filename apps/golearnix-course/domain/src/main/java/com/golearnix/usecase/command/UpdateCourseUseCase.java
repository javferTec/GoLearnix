package com.golearnix.usecase.command;

import com.golearnix.dto.command.CourseCommandDto;

import java.util.UUID;

public interface UpdateCourseUseCase {

  void execute(UUID courseId, CourseCommandDto data);

}
