package com.golearnix.mappers.specific;

import com.golearnix.domain.User;
import com.golearnix.entities.UserEntity;
import com.golearnix.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserJpaMapper extends GenericJpaMapper<User, UserEntity> {

}
