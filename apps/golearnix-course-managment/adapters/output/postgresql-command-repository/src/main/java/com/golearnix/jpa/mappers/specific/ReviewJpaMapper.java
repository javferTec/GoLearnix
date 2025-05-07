package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.Review;
import com.golearnix.jpa.entities.ReviewEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserJpaMapper.class}
)
public interface ReviewJpaMapper extends GenericJpaMapper<Review, ReviewEntity> {

}
