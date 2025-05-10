package com.golearnix.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.golearnix.common.annotations.Util;
import lombok.RequiredArgsConstructor;

@Util
@RequiredArgsConstructor
public class Cloner {

  private final ObjectMapper mapper;

  public <T> T copy(T original, Class<T> clazz) {
    return mapper.convertValue(original, clazz);
  }

}
