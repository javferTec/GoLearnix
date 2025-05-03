package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Progress {

  private Integer id;
  private User user;
  private Boolean completed;

  public void complete(User user) {
    this.user = user;
    this.completed = true;
  }

}
