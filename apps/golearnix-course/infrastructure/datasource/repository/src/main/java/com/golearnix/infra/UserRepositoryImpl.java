package com.golearnix.infra;

import com.golearnix.infra.driven.query.UserQueryPort;
import com.golearnix.model.User;
import com.golearnix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserQueryPort userQueryPort;

  @Override
  public User getById(UUID id) {
    return null;
  }
}
