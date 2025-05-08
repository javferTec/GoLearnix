package com.golearnix.common.utils.security;

import com.golearnix.common.annotations.UtilConfiguration;
import com.golearnix.common.utils.jwt.JwtRoleConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@UtilConfiguration
@EnableMethodSecurity
public class SecurityConfig {

  @Value("${api.base-path}")
  private String basePath;

  private final JwtDecoder jwtDecoder;
  private final JwtAuthenticationConverter jwtConverter;

  public SecurityConfig(JwtDecoder jwtDecoder, JwtRoleConverter roleConverter) {
    this.jwtDecoder   = jwtDecoder;
    this.jwtConverter = new JwtAuthenticationConverter();
    this.jwtConverter.setJwtGrantedAuthoritiesConverter(roleConverter);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET,
                basePath + "/courses",
                basePath + "/courses/*"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2
            .jwt(jwt -> jwt
                .decoder(jwtDecoder)
                .jwtAuthenticationConverter(jwtConverter)
            )
        );

    return http.build();
  }

}
