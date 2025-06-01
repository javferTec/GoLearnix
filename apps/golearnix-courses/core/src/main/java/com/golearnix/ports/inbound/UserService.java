package com.golearnix.ports.inbound;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.User;

import java.util.UUID;

public interface UserService {

  User getById(UUID id) throws ResourceNotFoundException;
  void delete(UUID id);

}
