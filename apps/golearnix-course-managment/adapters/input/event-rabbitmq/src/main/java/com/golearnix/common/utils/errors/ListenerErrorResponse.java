package com.golearnix.common.utils.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListenerErrorResponse {

  private String queue;
  private String reason;

}
