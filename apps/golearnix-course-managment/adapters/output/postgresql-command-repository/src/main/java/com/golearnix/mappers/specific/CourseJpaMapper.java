package com.golearnix.mappers.specific;

import com.golearnix.domain.Course;
import com.golearnix.entities.CourseEntity;
import com.golearnix.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserJpaMapper.class, CategoryJpaMapper.class, SectionJpaMapper.class,
            ReviewJpaMapper.class, EnrollmentJpaMapper.class}
)
public interface CourseJpaMapper extends GenericJpaMapper<Course, CourseEntity> {

}
