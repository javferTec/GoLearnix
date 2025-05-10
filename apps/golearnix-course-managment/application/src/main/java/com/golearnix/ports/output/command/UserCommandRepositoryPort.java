package com.golearnix.ports.output.command;

import com.golearnix.domain.User;

import java.util.UUID;

public interface UserCommandRepositoryPort {

  void delete(UUID id);
  void save(User user);

}
