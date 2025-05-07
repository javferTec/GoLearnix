package com.golearnix.mappers.specific;

import com.golearnix.domain.User;
import com.golearnix.entities.UserReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserRedisMapper extends GenericRedisMapper<User, UserReadModel> {

}
