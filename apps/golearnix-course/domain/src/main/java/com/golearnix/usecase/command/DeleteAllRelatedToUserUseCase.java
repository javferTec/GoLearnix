package com.golearnix.usecase.command;

import java.util.UUID;

public interface DeleteAllRelatedToUserUseCase {

  void execute(UUID userId);

}
