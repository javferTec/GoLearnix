package com.golearnix.usecase.query;

import com.golearnix.dto.query.getAllCourses.GetAllCoursesQueryDto;

import java.util.List;

public interface GetAllCoursesUseCase {

  List<GetAllCoursesQueryDto> execute();

}
