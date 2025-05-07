package com.golearnix;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRedisDocumentRepositories
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
