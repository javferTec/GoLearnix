package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Enrollment;
import com.golearnix.redis.entities.EnrollmentReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface EnrollmentRedisMapper extends GenericRedisMapper<Enrollment, EnrollmentReadModel> {

}
