package com.golearnix.mappers.specific;

import com.golearnix.domain.Category;
import com.golearnix.entities.CategoryEntity;
import com.golearnix.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryJpaMapper extends GenericJpaMapper<Category, CategoryEntity> {

}
