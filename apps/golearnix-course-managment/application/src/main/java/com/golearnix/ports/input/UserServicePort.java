package com.golearnix.ports.input;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.User;

import java.util.UUID;

public interface UserServicePort {

  User getById(UUID id) throws ResourceNotFoundException;

}
