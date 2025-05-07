package com.golearnix.redis.mappers.specific;

import com.golearnix.domain.Category;
import com.golearnix.redis.entities.CategoryReadModel;
import com.golearnix.redis.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryRedisMapper extends GenericRedisMapper<Category, CategoryReadModel> {

}
