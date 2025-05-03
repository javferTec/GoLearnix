package com.golearnix.common.utils.jwt;

import com.golearnix.common.annotations.Util;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;

/**
 * Convierte el claim "role" en una autoridad ROLE_{ROLE_EN_MAYÚSCULAS}.
 */
@Util
public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

  private static final String ROLE_CLAIM = "role";

  @Override
  public Collection<GrantedAuthority> convert(Jwt jwt) {

    String role = jwt.getClaimAsString(ROLE_CLAIM);
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
  }

}
