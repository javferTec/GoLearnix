package com.golearnix.mappers.specific;

import com.golearnix.domain.Lesson;
import com.golearnix.entities.LessonEntity;
import com.golearnix.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProgressJpaMapper.class}
)
public interface LessonJpaMapper extends GenericJpaMapper<Lesson, LessonEntity> {

}
