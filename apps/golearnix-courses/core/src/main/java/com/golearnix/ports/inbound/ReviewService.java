package com.golearnix.ports.inbound;

import com.golearnix.domain.Review;

import java.util.List;

public interface ReviewService {

  List<Review> getAllByIds(List<Integer> ids);

}
