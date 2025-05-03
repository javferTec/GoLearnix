package com.golearnix.ports.input;

import com.golearnix.domain.Review;

import java.util.List;

public interface ReviewServicePort {

  List<Review> getAllByIds(List<Integer> ids);

}
