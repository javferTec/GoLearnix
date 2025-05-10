package com.golearnix.jpa.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.User;
import com.golearnix.jpa.mappers.specific.UserJpaMapper;
import com.golearnix.jpa.repositories.UserEntityJpaRepository;
import com.golearnix.ports.output.command.UserCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class UserCommandRepositoryAdapter implements UserCommandRepositoryPort {

  private final UserEntityJpaRepository userEntityJpaRepository;
  private final UserJpaMapper userJpaMapper;

  @Override
  public void delete(UUID id) {
    userEntityJpaRepository.deleteById(id);
  }

  @Override
  public void save(User user) {
    userEntityJpaRepository.save(userJpaMapper.toJpaEntity(user));
  }

}
