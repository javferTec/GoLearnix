package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.User;
import com.golearnix.mappers.specific.UserRedisMapper;
import com.golearnix.ports.output.query.UserQueryRepositoryPort;
import com.golearnix.repositories.UserReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class UserQueryRepositoryAdapter implements UserQueryRepositoryPort {

  private final UserReadRepository userReadRepository;
  private final UserRedisMapper userRedisMapper;

  @Override
  public Optional<User> getById(UUID id) {
    return userReadRepository.findById(id)
        .map(userRedisMapper::toDomain);
  }

}
