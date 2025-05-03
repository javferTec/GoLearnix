package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  private Integer id;
  private User user;
  private int rating;
  private String comment;

}
