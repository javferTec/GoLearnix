package com.golearnix.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeletedEvent {

  private String event;
  private UserDataDetail data;

}
