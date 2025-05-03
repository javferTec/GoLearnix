package com.golearnix.service;

import com.golearnix.common.annotation.DomainService;
import com.golearnix.model.User;
import com.golearnix.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@DomainService
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getById(UUID id) {
    return null;
  }
}
