package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.Lesson;
import com.golearnix.jpa.entities.LessonEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProgressJpaMapper.class}
)
public interface LessonJpaMapper extends GenericJpaMapper<Lesson, LessonEntity> {

}
