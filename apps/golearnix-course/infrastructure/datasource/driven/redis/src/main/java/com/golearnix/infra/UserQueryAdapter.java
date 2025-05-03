package com.golearnix.infra;

import com.golearnix.infra.driven.query.UserQueryPort;
import com.golearnix.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryPort {

  @Override
  public User getById(UUID id) {
    return null;
  }
}
