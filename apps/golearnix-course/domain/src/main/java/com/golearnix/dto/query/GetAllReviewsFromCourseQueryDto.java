package com.golearnix.dto.query;

import com.golearnix.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllReviewsFromCourseQueryDto {

  private String title;
  private List<GetAllReviewsQueryDto> reviews;

}
