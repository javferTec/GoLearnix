package com.golearnix.redis.mappers.generic;

import org.mapstruct.Named;

public interface GenericRedisMapper<T, RM> {

  // Metodo de mapeo de modelo de lectura de redis a dominio
  @Named("toDomain")
  T toDomain(RM readModel);

  // Metodo de mapeo de dominio a modelo de lectura de redis
  @Named("toReadModel")
  RM toReadModel(T domain);

}
