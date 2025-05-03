package com.golearnix.service;

import com.golearnix.common.exception.ResourceNotFoundException;
import com.golearnix.common.exception.UserAlreadyEnrolledInCourse;
import com.golearnix.model.Course;
import com.golearnix.model.Lesson;
import com.golearnix.model.User;

import java.util.List;
import java.util.UUID;

public interface CourseService {

  // query
  List<Course> getAll();
  Course getById(UUID id) throws ResourceNotFoundException;

  // command
  Course create(Course course);
  Course update(Course source, Course target);
  Course delete(UUID id);
  Course enroll(Course course, User user) throws UserAlreadyEnrolledInCourse;
  Course completeLesson(Course course, Lesson lesson, User user);

}
