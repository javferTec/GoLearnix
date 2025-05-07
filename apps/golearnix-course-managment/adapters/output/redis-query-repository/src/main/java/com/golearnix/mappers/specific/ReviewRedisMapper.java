package com.golearnix.mappers.specific;

import com.golearnix.domain.Review;
import com.golearnix.entities.ReviewReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface ReviewRedisMapper extends GenericRedisMapper<Review, ReviewReadModel> {

}
