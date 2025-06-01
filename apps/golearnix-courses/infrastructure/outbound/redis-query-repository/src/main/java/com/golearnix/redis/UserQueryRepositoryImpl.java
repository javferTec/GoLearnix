package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.User;
import com.golearnix.ports.outbound.query.UserQueryRepository;
import com.golearnix.redis.mappers.specific.UserRedisMapper;
import com.golearnix.redis.repositories.UserReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {

  private final UserReadRepository userReadRepository;
  private final UserRedisMapper userRedisMapper;

  @Override
  public Optional<User> getById(UUID id) {
    return userReadRepository.findById(id)
        .map(userRedisMapper::toDomain);
  }

  @Override
  public void delete(UUID id) {
    userReadRepository.deleteById(id);
  }

}
