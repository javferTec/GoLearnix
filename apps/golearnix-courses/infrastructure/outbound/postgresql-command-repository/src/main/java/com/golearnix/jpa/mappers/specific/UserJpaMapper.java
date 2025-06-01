package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.User;
import com.golearnix.jpa.entities.UserEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserJpaMapper extends GenericJpaMapper<User, UserEntity> {

}
