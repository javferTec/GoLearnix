package com.golearnix.mappers.specific;

import com.golearnix.domain.Progress;
import com.golearnix.entities.ProgressReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface ProgressRedisMapper  extends GenericRedisMapper<Progress, ProgressReadModel> {

}
