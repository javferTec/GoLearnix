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
@Document("lesson")
public class LessonReadModel {

  @Id
  private Integer id;

  @Indexed
  private String title;

  private String content;
  private int order;
  private List<ProgressReadModel> progress;

}
