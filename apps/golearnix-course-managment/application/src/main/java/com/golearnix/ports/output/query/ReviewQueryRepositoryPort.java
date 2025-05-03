package com.golearnix.ports.output.query;

import com.golearnix.domain.Review;

import java.util.List;

public interface ReviewQueryRepositoryPort {

  List<Review> getAllByIds(List<Integer> ids);

}
