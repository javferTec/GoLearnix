package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Review;
import com.golearnix.domain.User;
import com.golearnix.mappers.specific.ReviewRedisMapper;
import com.golearnix.ports.output.query.ReviewQueryRepositoryPort;
import com.golearnix.repositories.ReviewReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class ReviewQueryRepositoryAdapter implements ReviewQueryRepositoryPort {

  private final ReviewReadRepository reviewReadRepository;
  private final ReviewRedisMapper reviewRedisMapper;

  @Override
  public List<Review> getAllByIds(List<Integer> ids) {
    return reviewReadRepository.findAllById(ids)
        .stream()
        .map(reviewRedisMapper::toDomain)
        .toList();
  }

}
