package com.golearnix.entities;

import com.redis.om.spring.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class UserReadModel {

  @Id
  private UUID id;

}
