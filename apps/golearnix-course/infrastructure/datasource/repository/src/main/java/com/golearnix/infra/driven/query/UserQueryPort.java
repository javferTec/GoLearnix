package com.golearnix.infra.driven.query;

import com.golearnix.model.User;

import java.util.UUID;

public interface UserQueryPort {

  User getById(UUID id);

}
