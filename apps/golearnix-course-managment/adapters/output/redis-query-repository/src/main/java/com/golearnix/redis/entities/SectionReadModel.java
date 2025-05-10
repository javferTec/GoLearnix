package com.golearnix.redis.entities;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("section")
public class SectionReadModel {

  @Id
  private Integer id;

  @Indexed
  private String title;

  private int order;
  private List<LessonReadModel> lessons;

}
