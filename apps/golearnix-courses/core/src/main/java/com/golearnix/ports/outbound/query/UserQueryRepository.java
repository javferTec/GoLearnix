package com.golearnix.ports.outbound.query;

import com.golearnix.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserQueryRepository {

  Optional<User> getById(UUID id);
  void delete(UUID id);

}
