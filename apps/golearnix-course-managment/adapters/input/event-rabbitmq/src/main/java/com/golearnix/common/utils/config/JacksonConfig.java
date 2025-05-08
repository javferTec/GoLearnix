package com.golearnix.common.utils.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.golearnix.common.annotations.UtilConfiguration;
import org.springframework.context.annotation.Bean;

@UtilConfiguration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return JsonMapper.builder()
        .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
        .enable(com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .build();
  }

}
