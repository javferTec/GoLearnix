package com.golearnix.mappers.specific;

import com.golearnix.domain.Section;
import com.golearnix.entities.SectionReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {LessonRedisMapper.class}
)
public interface SectionRedisMapper extends GenericRedisMapper<Section, SectionReadModel> {

}
