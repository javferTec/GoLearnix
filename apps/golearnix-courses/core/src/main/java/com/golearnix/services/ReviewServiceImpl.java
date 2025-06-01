package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.domain.Review;
import com.golearnix.ports.inbound.ReviewService;
import com.golearnix.ports.outbound.query.ReviewQueryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewQueryRepository reviewQueryRepositoryPort;

  @Override
  public List<Review> getAllByIds(List<Integer> ids) {
    return reviewQueryRepositoryPort.getAllByIds(ids);
  }

}
