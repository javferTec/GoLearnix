package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Review;

import java.util.List;

public interface ReviewQueryRepository {

  List<Review> getAllByIds(List<Integer> ids);

}
