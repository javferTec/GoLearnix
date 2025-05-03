package com.golearnix.usecase.command;

import com.golearnix.dto.command.CourseCommandDto;

public interface CreateCourseUseCase {

  void execute(CourseCommandDto data);

}
