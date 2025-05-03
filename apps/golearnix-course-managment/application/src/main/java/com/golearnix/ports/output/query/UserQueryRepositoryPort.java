package com.golearnix.ports.output.query;

import com.golearnix.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserQueryRepositoryPort {

  Optional<User> getById(UUID id);

}
