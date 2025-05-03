package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Category;
import com.golearnix.ports.input.CategoryServicePort;
import com.golearnix.ports.output.query.CategoryQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CategoryServiceUseCase implements CategoryServicePort {

  private final CategoryQueryRepositoryPort categoryQueryRepositoryPort;

  @Override
  public Category getById(Integer id) throws ResourceNotFoundException {
    return categoryQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
  }

}
