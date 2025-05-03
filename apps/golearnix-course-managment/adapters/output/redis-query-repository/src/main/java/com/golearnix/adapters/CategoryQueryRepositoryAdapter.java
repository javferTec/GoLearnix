package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Category;
import com.golearnix.ports.output.query.CategoryQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class CategoryQueryRepositoryAdapter implements CategoryQueryRepositoryPort {

  @Override
  public Optional<Category> getById(Integer id) {
    Category category = new Category(1, "Category 1", "Description 1");
    return Optional.of(category);
  }

}
