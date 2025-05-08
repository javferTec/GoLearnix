package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.User;
import com.golearnix.ports.input.UserServicePort;
import com.golearnix.ports.output.command.UserCommandRepositoryPort;
import com.golearnix.ports.output.query.UserQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class UserServiceUseCase implements UserServicePort {

  private final UserQueryRepositoryPort userQueryRepositoryPort;
  private final UserCommandRepositoryPort userCommandRepositoryPort;

  @Override
  public User getById(UUID id) throws ResourceNotFoundException {
    return userQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  @Override
  public void delete(UUID id) {
    getById(id);
    userCommandRepositoryPort.delete(id);
  }

}
