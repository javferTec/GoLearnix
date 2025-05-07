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
@Document("lesson")
public class LessonReadModel {

  @Id
  private Integer id;

  @Indexed
  private String title;

  private int order;
  private String content;
  private List<ProgressReadModel> progress;

}
