package com.golearnix.service;

import com.golearnix.common.exception.ResourceNotFoundException;
import com.golearnix.model.User;

import java.util.UUID;

public interface UserService {

  // query
  User getById(UUID id) throws ResourceNotFoundException;

}
