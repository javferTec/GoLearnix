package com.golearnix.dto.query;

import com.golearnix.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllReviewsQueryDto {

  private UUID id;
  private GetAllUsersQueryDto user;
  private int rating;
  private String comment;

}
