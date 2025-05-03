package com.golearnix.repository;

import com.golearnix.model.User;

import java.util.UUID;

public interface UserRepository {

  // query
  User getById(UUID id);

}
