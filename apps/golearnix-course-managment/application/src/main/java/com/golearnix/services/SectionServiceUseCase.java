package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Section;
import com.golearnix.ports.input.SectionServicePort;
import com.golearnix.ports.output.query.SectionQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class SectionServiceUseCase implements SectionServicePort {

  private final SectionQueryRepositoryPort sectionQueryRepositoryPort;

  @Override
  public Section getById(Integer id) {
    return sectionQueryRepositoryPort.getById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));
  }

  @Override
  public List<Section> getAllByIds(List<Integer> ids) {
    return sectionQueryRepositoryPort.getAllByIds(ids);
  }

}
