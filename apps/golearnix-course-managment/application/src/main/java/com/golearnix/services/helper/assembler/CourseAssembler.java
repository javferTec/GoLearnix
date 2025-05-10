package com.golearnix.services.helper.assembler;

import com.golearnix.common.annotations.Assembler;
import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Review;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.ports.input.CategoryServicePort;
import com.golearnix.ports.input.EnrollmentServicePort;
import com.golearnix.ports.input.ReviewServicePort;
import com.golearnix.ports.input.SectionServicePort;
import com.golearnix.ports.input.UserServicePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Assembler
@RequiredArgsConstructor
public class CourseAssembler {

  private final UserServicePort userServicePort;
  private final CategoryServicePort categoryServicePort;


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
