package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Review;
import com.golearnix.redis.entities.ReviewReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserRedisMapper.class}
)
public interface ReviewRedisMapper extends GenericRedisMapper<Review, ReviewReadModel> {

}
