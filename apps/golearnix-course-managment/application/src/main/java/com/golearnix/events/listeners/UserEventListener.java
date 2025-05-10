package com.golearnix.events.listeners;

import com.golearnix.common.annotations.Listener;
import com.golearnix.domain.User;
import com.golearnix.events.UserDeletedEvent;
import com.golearnix.ports.output.command.UserCommandRepositoryPort;
import com.golearnix.ports.output.query.UserQueryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@Listener
@RequiredArgsConstructor
public class UserEventListener {

  private final UserQueryRepositoryPort userQueryRepositoryPort;
  private final UserCommandRepositoryPort userCommandRepositoryPort;


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
