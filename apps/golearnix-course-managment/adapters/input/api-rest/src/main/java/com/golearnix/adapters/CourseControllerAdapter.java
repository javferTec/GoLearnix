package com.golearnix.adapters;

import com.golearnix.common.utils.helpers.CurrentUserHelper;
import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.ports.input.CourseServicePort;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(CourseControllerAdapter.URL)
@CrossOrigin(origins = "${api.cors.allowed-origins}")
@Tag(name = "Courses", description = "Controller used to course management") // TODO: Swagger
public class CourseControllerAdapter {

  protected static final String URL = "${api.base-path}" + "/courses";

  private final CourseServicePort courseServicePort;
  private final CurrentUserHelper currentUserHelper;

  @GetMapping
  public ResponseEntity<List<CourseGetAllProjection>> getAll() {
    return ResponseEntity.ok(courseServicePort.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Course> getById(@PathVariable Integer id) {
    return ResponseEntity.ok(courseServicePort.getById(id));
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
  public ResponseEntity<Void> create(Course course) {
    courseServicePort.create(course);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
  public ResponseEntity<Void> update(@PathVariable Integer id, Course course) {
    courseServicePort.update(id, course);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    courseServicePort.delete(id);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{courseId}/enrollments")
  @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
  public ResponseEntity<Void> enroll(@PathVariable Integer courseId) {
    UUID userId = currentUserHelper.getId();
    courseServicePort.enroll(courseId, userId);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/complete")
  @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
  public ResponseEntity<Void> completeLesson(@PathVariable Integer courseId, @PathVariable Integer sectionId, @PathVariable Integer lessonId) {
    UUID userId = currentUserHelper.getId();
    courseServicePort.completeLesson(courseId, sectionId, lessonId, userId);
    return ResponseEntity.ok().build();
  }

}
