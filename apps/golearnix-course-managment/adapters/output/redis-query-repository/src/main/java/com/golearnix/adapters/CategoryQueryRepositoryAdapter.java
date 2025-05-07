package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Category;
import com.golearnix.mappers.specific.CategoryRedisMapper;
import com.golearnix.ports.output.query.CategoryQueryRepositoryPort;
import com.golearnix.repositories.CategoryReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class CategoryQueryRepositoryAdapter implements CategoryQueryRepositoryPort {

  private final CategoryReadRepository categoryReadRepository;
  private final CategoryRedisMapper categoryRedisMapper;

  @Override
  public Optional<Category> getById(Integer id) {
    return categoryReadRepository.findById(id)
        .map(categoryRedisMapper::toDomain);
  }

}
