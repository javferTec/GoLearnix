package com.golearnix.common.dto.messages;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDeletedEvent {

  @NotBlank
  private String event;

  @Valid
  private UserDataDetail data;

}
