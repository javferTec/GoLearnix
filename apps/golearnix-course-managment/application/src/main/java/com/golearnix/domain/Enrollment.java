package com.golearnix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

  private Integer id;
  private User user;

  public void enrollUser(User user) {
    this.user = user;
  }

}
