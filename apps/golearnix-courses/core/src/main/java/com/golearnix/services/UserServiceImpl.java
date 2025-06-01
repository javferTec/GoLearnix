package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.User;
import com.golearnix.events.publishers.UserEventPublisher;
import com.golearnix.ports.inbound.UserService;
import com.golearnix.ports.outbound.command.UserCommandRepository;
import com.golearnix.ports.outbound.query.UserQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserQueryRepository userQueryRepositoryPort;
  private final UserCommandRepository userCommandRepositoryPort;

  private final UserEventPublisher userEventPublisher;

  @Override
  public User getById(UUID id) throws ResourceNotFoundException {
    return userQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    User oldUser = getById(id);

    userCommandRepositoryPort.delete(id);
    userEventPublisher.publishUserDeleted(oldUser);
  }

}
