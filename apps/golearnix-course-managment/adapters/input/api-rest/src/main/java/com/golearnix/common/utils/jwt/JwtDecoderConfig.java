package com.golearnix.common.utils.jwt;

import com.golearnix.common.annotations.UtilConfiguration;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.spec.SecretKeySpec;

@UtilConfiguration
public class JwtDecoderConfig {

  @Bean
  public JwtDecoder jwtDecoder(@Value("${jwt.secret}") String hexSecret) throws DecoderException {

    byte[] keyBytes = Hex.decodeHex(hexSecret.toCharArray());
    SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");

    return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
  }

}
