package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.Progress;
import com.golearnix.jpa.entities.ProgressEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserJpaMapper.class}
)
public interface ProgressJpaMapper extends GenericJpaMapper<Progress, ProgressEntity> {

}
