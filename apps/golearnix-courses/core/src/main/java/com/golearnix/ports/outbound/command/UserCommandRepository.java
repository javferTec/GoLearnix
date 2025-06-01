package com.golearnix.ports.outbound.command;

import com.golearnix.domain.User;

import java.util.UUID;

public interface UserCommandRepository {

  void delete(UUID id);
  void save(User user);

}
