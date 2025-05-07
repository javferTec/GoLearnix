package com.golearnix.entities;

import com.redis.om.spring.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("enrollment")
public class EnrollmentReadModel {

  @Id
  private Integer id;
  private UserReadModel user;

}
