package com.golearnix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

  private UUID id;
  private String title;
  private String description;
  private User instructor;
  private Category category;
  private List<Section> sections;
  private List<Review> reviews;
  private List<Enrollment> enrollments;

}
