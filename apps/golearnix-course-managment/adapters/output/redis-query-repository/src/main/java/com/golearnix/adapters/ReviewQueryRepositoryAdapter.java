package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Review;
import com.golearnix.domain.User;
import com.golearnix.ports.output.query.ReviewQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class ReviewQueryRepositoryAdapter implements ReviewQueryRepositoryPort {

  @Override
  public List<Review> getAllByIds(List<Integer> ids) {
    Review review = new Review(1, new User(UUID.randomUUID()), 5, "Great course!");
    return List.of(review);
  }

}
