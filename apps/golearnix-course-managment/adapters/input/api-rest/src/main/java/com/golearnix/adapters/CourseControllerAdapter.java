package com.golearnix.adapters;

import com.golearnix.common.utils.helpers.CurrentUserHelper;
import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.ports.input.CourseServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(CourseControllerAdapter.URL)
@CrossOrigin(origins = "${api.cors.allowed-origins}")
@Tag(name = "Courses", description = "Controller used to course management")
public class CourseControllerAdapter {

  protected static final String URL = "${api.base-path}" + "/courses";

  private final CourseServicePort courseServicePort;
  private final CurrentUserHelper currentUserHelper;

  @Operation(summary = "Get all courses", description = "Retrieve a list of all available courses")
  @ApiResponse(responseCode = "200", description = "Successful retrieval of courses")
  @GetMapping
  public ResponseEntity<List<CourseGetAllProjection>> getAll() {
    return ResponseEntity.ok(courseServicePort.getAll());
  }

  @Operation(summary = "Get a course by ID", description = "Retrieve a course by its ID")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Course found"),
      @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<Course> getById(@PathVariable Integer id) {
    return ResponseEntity.ok(courseServicePort.getById(id));
  }

  @Operation(summary = "Create a new course", description = "Create a course (INSTRUCTOR role only)")
  @ApiResponse(responseCode = "200", description = "Course successfully created")
  @PostMapping
  @PreAuthorize("hasRole(@role.INSTRUCTOR)")
  public ResponseEntity<Void> create(@RequestBody @Valid Course course) {
    courseServicePort.create(course);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Update a course", description = "Update an existing course by ID (INSTRUCTOR role only)")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Course successfully updated"),
      @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
  })
  @PutMapping("/{id}")
  @PreAuthorize("hasRole(@role.INSTRUCTOR)")
  public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody @Valid Course course) {
    courseServicePort.update(id, course);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Delete a course", description = "Delete a course by ID (INSTRUCTOR role only)")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "Course successfully deleted"),
      @ApiResponse(responseCode = "404", description = "Course not found", content = @Content)
  })
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole(@role.INSTRUCTOR)")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    courseServicePort.delete(id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Enroll in a course", description = "Enroll current user into a course (STUDENT role only)")
  @ApiResponse(responseCode = "200", description = "Enrollment successful")
  @PostMapping("/{courseId}/enrollments")
  @PreAuthorize("hasRole(@role.STUDENT)")
  public ResponseEntity<Void> enroll(@PathVariable Integer courseId) {
    UUID userId = currentUserHelper.getId();
    courseServicePort.enroll(courseId, userId);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Complete a lesson", description = "Mark a lesson as completed by the user (STUDENT role only)")
  @ApiResponse(responseCode = "200", description = "Lesson marked as completed")
  @PostMapping("/{courseId}/sections/{sectionId}/lessons/{lessonId}/complete")
  @PreAuthorize("hasRole(@role.STUDENT)")
  public ResponseEntity<Void> completeLesson(@PathVariable Integer courseId, @PathVariable Integer sectionId, @PathVariable Integer lessonId) {
    UUID userId = currentUserHelper.getId();
    courseServicePort.completeLesson(courseId, sectionId, lessonId, userId);
    return ResponseEntity.ok().build();
  }

}
