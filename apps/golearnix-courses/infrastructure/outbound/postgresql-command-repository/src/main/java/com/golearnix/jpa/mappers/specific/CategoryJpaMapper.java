package com.golearnix.jpa.mappers.specific;

import com.golearnix.domain.Category;
import com.golearnix.jpa.entities.CategoryEntity;
import com.golearnix.jpa.mappers.generic.GenericJpaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CategoryJpaMapper extends GenericJpaMapper<Category, CategoryEntity> {

}
