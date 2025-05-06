package com.golearnix.mappers.specific;

import com.golearnix.domain.Section;
import com.golearnix.entities.SectionEntity;
import com.golearnix.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {LessonJpaMapper.class}
)
public interface SectionJpaMapper extends GenericJpaMapper<Section, SectionEntity> {

}
