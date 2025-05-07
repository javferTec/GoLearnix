package com.golearnix;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.golearnix.jpa")
@EnableRedisDocumentRepositories(basePackages = "com.golearnix.redis")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
