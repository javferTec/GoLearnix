package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Course;
import com.golearnix.ports.output.command.CourseCommandRepositoryPort;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class CourseCommandRepositoryAdapter implements CourseCommandRepositoryPort {

  @Override
  public void save(Course course) {
    System.out.println("Saving course: " + course);
  }

  @Override
  public void delete(Integer id) {
    System.out.println("Deleting course with id: " + id);
  }

  @Override
  public void enroll(Course course) {
    System.out.println("Enrolling in course: " + course);
  }

  @Override
  public void completeLesson(Course course) {
    System.out.println("Completing lesson in course: " + course);
  }

}
