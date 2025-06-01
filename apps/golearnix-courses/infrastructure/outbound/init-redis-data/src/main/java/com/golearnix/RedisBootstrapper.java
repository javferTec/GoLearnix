package com.golearnix;

import com.golearnix.common.annotations.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Util
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.redis.bootstrap", havingValue = "true")
public class RedisBootstrapper implements ApplicationListener<ApplicationReadyEvent> {

  private final DeleteRedisData deleteRedisData;
  private final CreateRedisData createRedisData;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    deleteRedisData.deleteAll();
    createRedisData.createAll();
  }

}
