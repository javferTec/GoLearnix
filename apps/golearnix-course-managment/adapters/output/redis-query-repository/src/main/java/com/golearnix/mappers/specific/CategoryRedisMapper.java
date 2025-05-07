package com.golearnix.mappers.specific;

import com.golearnix.domain.Category;
import com.golearnix.entities.CategoryReadModel;
import com.golearnix.mappers.generic.GenericRedisMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryRedisMapper extends GenericRedisMapper<Category, CategoryReadModel> {

}
