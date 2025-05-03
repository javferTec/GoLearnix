package com.golearnix.dto.query.getAllCourses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCoursesQueryDto {

  private String title;
  private String description;

}
