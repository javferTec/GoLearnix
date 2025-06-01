package com.golearnix.services.helper.assembler;

import com.golearnix.common.annotations.Assembler;
import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.User;
import com.golearnix.ports.inbound.CategoryService;
import com.golearnix.ports.inbound.UserService;
import lombok.RequiredArgsConstructor;

@Assembler
@RequiredArgsConstructor
public class CourseAssembler {

  private final UserService userServicePort;
  private final CategoryService categoryServicePort;


  public Course assemble(Course course) {
    return assemble(course, course);
  }

  public Course assemble(Course target, Course source) {
    target.setTitle(source.getTitle());
    target.setDescription(source.getDescription());
    target.setInstructor(resolveInstructor(source));
    target.setCategory(resolveCategory(source));

    return target;
  }

  private User resolveInstructor(Course course) {
    return userServicePort.getById(course.getInstructor().getId());
  }

  private Category resolveCategory(Course course) {
    return categoryServicePort.getById(course.getCategory().getId());
  }

}
