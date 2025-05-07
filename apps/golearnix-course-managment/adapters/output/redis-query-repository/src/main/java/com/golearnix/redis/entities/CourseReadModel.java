package com.golearnix.redis.entities;

import com.redis.om.spring.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("course")
public class CourseReadModel {

  @Id
  private Integer id;

  @Indexed
  private String title;

  private String description;
  private UserReadModel instructor;
  private CategoryReadModel category;
  private List<SectionReadModel> sections;
  private List<ReviewReadModel> reviews;
  private List<EnrollmentReadModel> enrollments;

}
