package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Course;
import com.golearnix.redis.entities.CourseReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class, CategoryRedisMapper.class, SectionRedisMapper.class,
        ReviewRedisMapper.class, EnrollmentRedisMapper.class}
)
public interface CourseRedisMapper  extends GenericRedisMapper<Course, CourseReadModel> {

}
