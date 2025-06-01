package com.golearnix.common.utils.jwt;

import com.golearnix.common.annotations.Util;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Util
public class JwtUtil {

  private Jwt getJwt() {
    return (Jwt) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
  }

  public UUID getUserId() {
    String sub = getJwt().getSubject();
    return UUID.fromString(sub);
  }

  public String getRole() {
    return getJwt().getClaimAsString("role");
  }

}
