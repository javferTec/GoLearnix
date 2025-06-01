package com.golearnix.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  private Integer id;

  @NotNull(message = "Title is required")
  @NotEmpty(message = "Title cannot be empty")
  private String title;

  @NotNull(message = "Description is required")
  @NotEmpty(message = "Description cannot be empty")
  private String description;

  @NotNull(message = "Instructor is required")
  private User instructor;

  @NotNull(message = "Category is required")
  private Category category;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<Section> sections;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<Review> reviews;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private List<Enrollment> enrollments;

  public void addSections(List<Section> sections) {
    this.sections.addAll(sections);
  }

  public void addReviews(List<Review> reviews) {
    this.reviews.addAll(reviews);
  }

  public void addEnrollments(List<Enrollment> enrollments) {
    this.enrollments.addAll(enrollments);
  }

}
