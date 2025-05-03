package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.domain.Review;
import com.golearnix.ports.input.ReviewServicePort;
import com.golearnix.ports.output.query.ReviewQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class ReviewServiceUseCase implements ReviewServicePort {

  private final ReviewQueryRepositoryPort reviewQueryRepositoryPort;

  @Override
  public List<Review> getAllByIds(List<Integer> ids) {
    return reviewQueryRepositoryPort.getAllByIds(ids);
  }

}
