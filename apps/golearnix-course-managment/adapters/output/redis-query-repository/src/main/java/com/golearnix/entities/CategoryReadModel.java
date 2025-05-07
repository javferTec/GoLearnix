package com.golearnix.entities;

import com.redis.om.spring.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("category")
public class CategoryReadModel {

  @Id
  private Integer id;

  @Indexed
  private String name;

  private String description;

}
