package com.golearnix.events.listeners;

import com.golearnix.common.annotations.Listener;
import com.golearnix.domain.User;
import com.golearnix.events.UserDeletedEvent;
import com.golearnix.ports.outbound.command.UserCommandRepository;
import com.golearnix.ports.outbound.query.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@Listener
@RequiredArgsConstructor
public class UserEventListener {

  private final UserQueryRepository userQueryRepositoryPort;
  private final UserCommandRepository userCommandRepositoryPort;


  @EventListener
  public void onUserDeleted(UserDeletedEvent event) {

    User oldUser = event.oldUser();

    try {
      userQueryRepositoryPort.delete(oldUser.getId());
    } catch (Exception redisEx) {
      userCommandRepositoryPort.save(oldUser);
    }

  }

}
