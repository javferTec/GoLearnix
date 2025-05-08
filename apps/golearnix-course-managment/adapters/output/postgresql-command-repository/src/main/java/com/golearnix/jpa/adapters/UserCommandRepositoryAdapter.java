package com.golearnix.jpa.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
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

  @Override
  public void delete(UUID id) {
    userEntityJpaRepository.deleteById(id);
  }
}
