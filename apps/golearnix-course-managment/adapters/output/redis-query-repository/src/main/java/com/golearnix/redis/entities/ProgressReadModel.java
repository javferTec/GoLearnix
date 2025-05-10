package com.golearnix.redis.entities;

import com.redis.om.spring.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import com.redis.om.spring.annotations.Indexed;
import org.springframework.data.annotation.Reference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("progress")
public class ProgressReadModel {

  @Id
  private Integer id;

  private UserReadModel user;
  private Boolean completed;

}
