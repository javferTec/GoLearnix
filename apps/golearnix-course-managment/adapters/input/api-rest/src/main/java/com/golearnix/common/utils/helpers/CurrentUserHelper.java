package com.golearnix.common.utils.helpers;

import com.golearnix.common.annotations.Util;
import com.golearnix.common.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Util
@RequiredArgsConstructor
public class CurrentUserHelper {

  private final JwtUtil jwtUtil;

  public UUID getId() {
    return jwtUtil.getUserId();
  }

  public String getRole() {
    return jwtUtil.getRole();
  }

}
