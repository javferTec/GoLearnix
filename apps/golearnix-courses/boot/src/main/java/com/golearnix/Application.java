package com.golearnix;

import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.golearnix"})
@EnableRedisDocumentRepositories(basePackages = {"com.golearnix.redis"})
@EnableJpaRepositories(basePackages = {"com.golearnix.jpa"})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
