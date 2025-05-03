package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  private Integer id;
  private String title;
  private String description;
  private User instructor;
  private Category category;
  private List<Section> sections;
  private List<Review> reviews;
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
