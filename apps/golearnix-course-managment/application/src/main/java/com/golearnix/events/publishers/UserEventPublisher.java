package com.golearnix.events.publishers;

import com.golearnix.common.annotations.Publisher;
import com.golearnix.domain.User;
import com.golearnix.events.UserDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Publisher
@RequiredArgsConstructor
public class UserEventPublisher {

  private final ApplicationEventPublisher publisher;

  public void publishUserDeleted(User oldUser) {
    publisher.publishEvent(new UserDeletedEvent(oldUser));
  }

}
