package com.golearnix.mappers.generic;

import org.mapstruct.Named;

public interface GenericJpaMapper<T, E> {

  // Metodo de mapeo de entidad a dominio
  @Named("toDomain")
  T toDomain(E entity);

  // Metodo de mapeo de dominio a entidad
  @Named("toJpaEntity")
  E toJpaEntity(T domain);

}

