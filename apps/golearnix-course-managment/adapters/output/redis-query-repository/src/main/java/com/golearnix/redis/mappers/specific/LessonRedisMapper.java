package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Lesson;
import com.golearnix.redis.entities.LessonReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProgressRedisMapper.class}
)
public interface LessonRedisMapper  extends GenericRedisMapper<Lesson, LessonReadModel> {

}
