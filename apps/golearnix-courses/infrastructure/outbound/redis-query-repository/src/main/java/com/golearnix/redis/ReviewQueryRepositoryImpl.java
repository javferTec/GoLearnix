package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Review;
import com.golearnix.ports.outbound.query.ReviewQueryRepository;
import com.golearnix.redis.mappers.specific.ReviewRedisMapper;
import com.golearnix.redis.repositories.ReviewReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class ReviewQueryRepositoryImpl implements ReviewQueryRepository {

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
