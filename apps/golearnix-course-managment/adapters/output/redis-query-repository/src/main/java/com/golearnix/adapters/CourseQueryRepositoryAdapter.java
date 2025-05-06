package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Review;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.ports.output.query.CourseQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class CourseQueryRepositoryAdapter implements CourseQueryRepositoryPort{

  @Override
  public List<CourseGetAllProjection> getAll() {
    System.out.println("Fetching all courses");
    return List.of();
  }

  @Override
  public Optional<Course> getById(Integer id) {
    Course course = new Course(
        1,
        "Course 1",
        "Description 1",
        new User(UUID.randomUUID()),
        new Category(1, "Category 1", "Description 1"),
        List.of(new Section(1, "Section 1", 1, List.of(new Lesson(1, "Lesson 1", 1, "content",   List.of(new Progress(1, new User(UUID.randomUUID()), true)))))),
        List.of(new Review(1, new User(UUID.randomUUID()), 5, "Great course!")),
        List.of(new Enrollment(1, new User(UUID.randomUUID())))
    );

    System.out.println("Fetching course by id: " + id);
    return Optional.of(course);
  }

}
