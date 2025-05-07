package com.golearnix.mappers.specific;

import com.golearnix.domain.Enrollment;
import com.golearnix.entities.EnrollmentReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface EnrollmentRedisMapper extends GenericRedisMapper<Enrollment, EnrollmentReadModel> {

}
