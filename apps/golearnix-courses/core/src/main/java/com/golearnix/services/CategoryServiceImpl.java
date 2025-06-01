package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Category;
import com.golearnix.ports.inbound.CategoryService;
import com.golearnix.ports.outbound.query.CategoryQueryRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryQueryRepository categoryQueryRepositoryPort;

  @Override
  public Category getById(Integer id) throws ResourceNotFoundException {
    return categoryQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
  }

}
