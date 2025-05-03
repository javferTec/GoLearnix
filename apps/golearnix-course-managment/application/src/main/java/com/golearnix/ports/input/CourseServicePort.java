package com.golearnix.ports.input;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.common.exceptions.UserAlreadyEnrolledInCourse;
import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;

import java.util.List;
import java.util.UUID;

public interface CourseServicePort {

  List<CourseGetAllProjection> getAll();
  Course getById(Integer id) throws ResourceNotFoundException;
  void create(Course course);
  void update(Integer id, Course course);
  void delete(Integer id);
  void enroll(Integer courseId, UUID userId) throws UserAlreadyEnrolledInCourse;
  void completeLesson(Integer courseId, Integer sectionId, Integer lessonId, UUID userId);

}
