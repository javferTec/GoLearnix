package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.common.exceptions.UserAlreadyEnrolledInCourse;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.ports.input.CourseServicePort;
import com.golearnix.ports.input.LessonServicePort;
import com.golearnix.ports.input.SectionServicePort;
import com.golearnix.ports.input.UserServicePort;
import com.golearnix.ports.output.command.CourseCommandRepositoryPort;
import com.golearnix.ports.output.query.CourseQueryRepositoryPort;
import com.golearnix.services.helper.assembler.CourseAssembler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class CourseServiceUseCase implements CourseServicePort {

  private final CourseCommandRepositoryPort courseCommandRepositoryPort;
  private final CourseQueryRepositoryPort courseQueryRepositoryPort;

  private final CourseAssembler courseAssembler;

  private final UserServicePort userServicePort;
  private final SectionServicePort sectionServicePort;
  private final LessonServicePort lessonServicePort;

  @Override
  public List<CourseGetAllProjection> getAll() {
    return courseQueryRepositoryPort.getAll();
  }

  @Override
  public Course getById(Integer id) throws ResourceNotFoundException {
    return courseQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
  }

  @Override
  @Transactional
  public void create(Course course) {
    Course newCourse = courseAssembler.assemble(course);
    courseCommandRepositoryPort.save(newCourse);
    courseQueryRepositoryPort.save(newCourse);
  }

  @Override
  @Transactional
  public void update(Integer id, Course course) {
    Course newCourse = courseAssembler.assemble(getById(id), course);
    courseCommandRepositoryPort.save(newCourse);
    courseQueryRepositoryPort.update(newCourse);
  }

  @Override
  @Transactional
  public void delete(Integer id) {
    getById(id);
    courseCommandRepositoryPort.delete(id);
    courseQueryRepositoryPort.delete(id);
  }

  @Override
  @Transactional
  public void enroll(Integer courseId, UUID userId) throws UserAlreadyEnrolledInCourse {

    Course course = getById(courseId);

    if (course.getEnrollments().stream()
        .anyMatch(enrollment -> enrollment.getUser().getId().equals(userId))) {
      throw new UserAlreadyEnrolledInCourse("User already enrolled in course with id: " + courseId);
    }

    User user = userServicePort.getById(userId);

    Enrollment enrollment = new Enrollment();
    enrollment.enrollUser(user);

    course.addEnrollments(List.of(enrollment));
    courseCommandRepositoryPort.save(course);
    courseQueryRepositoryPort.update(course);
  }

  @Override
  @Transactional
  public void completeLesson(Integer courseId, Integer sectionId, Integer lessonId, UUID userId) {

    Course course = getById(courseId);
    User user = userServicePort.getById(userId);
    Lesson lesson = lessonServicePort.getById(lessonId);
    Section section = sectionServicePort.getById(sectionId);

    if (!course.getSections().contains(section)) {
      throw new ResourceNotFoundException("Section not found in course with id: " + courseId);
    }

    if (!section.getLessons().contains(lesson)) {
      throw new ResourceNotFoundException("Lesson not found in section with id: " + sectionId);
    }

    Optional<Progress> maybeProgress = lesson.getProgress()
        .stream()
        .filter(progress -> progress.getUser().getId().equals(userId))
        .findFirst();

    Progress progress = maybeProgress.orElseGet(() -> {
      Progress newProgress = new Progress();

      newProgress.complete(user);

      lesson.addProgresses(List.of(newProgress));
      return newProgress;
    });

    lesson.addProgresses(List.of(progress));

    courseCommandRepositoryPort.save(course);
    courseQueryRepositoryPort.update(course);
  }

}
