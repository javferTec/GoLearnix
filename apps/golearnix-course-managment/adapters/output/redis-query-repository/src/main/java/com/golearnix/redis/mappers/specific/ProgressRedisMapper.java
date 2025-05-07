package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Progress;
import com.golearnix.redis.entities.ProgressReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface ProgressRedisMapper  extends GenericRedisMapper<Progress, ProgressReadModel> {

}
