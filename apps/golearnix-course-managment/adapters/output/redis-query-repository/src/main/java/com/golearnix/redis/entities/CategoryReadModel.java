package com.golearnix.redis.entities;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("course_category")
public class CategoryReadModel {

  @Id
  private Integer id;

  @Indexed
  private String name;

  private String description;

}
