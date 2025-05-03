package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.User;
import com.golearnix.ports.output.query.UserQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepositoryPort {

  @Override
  public Optional<User> getById(UUID id) {
    return Optional.of(new User(UUID.randomUUID()));
  }

}
