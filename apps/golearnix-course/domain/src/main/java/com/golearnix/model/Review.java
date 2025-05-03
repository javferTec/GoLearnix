package com.golearnix.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  private UUID id;
  private User user;
  private int rating;
  private String comment;

}
