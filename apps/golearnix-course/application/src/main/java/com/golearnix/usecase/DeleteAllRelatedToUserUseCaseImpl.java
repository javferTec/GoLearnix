package com.golearnix.usecase;

import com.golearnix.common.annotation.UseCase;
import com.golearnix.usecase.DeleteAllRelatedToUserUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class DeleteAllRelatedToUserUseCaseImpl implements DeleteAllRelatedToUserUseCase {

  @Override
  public void execute(UUID userId) {

  }
}
