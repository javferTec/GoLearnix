package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.Enrollment;
import com.golearnix.jpa.entities.EnrollmentEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserJpaMapper.class}
)
public interface EnrollmentJpaMapper extends GenericJpaMapper<Enrollment, EnrollmentEntity> {

}
