package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.mappers.specific.SectionRedisMapper;
import com.golearnix.ports.output.query.SectionQueryRepositoryPort;
import com.golearnix.repositories.SectionReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class SectionQueryRepositoryAdapter implements SectionQueryRepositoryPort {

  private final SectionReadRepository sectionReadRepository;
  private final SectionRedisMapper sectionRedisMapper;

  @Override
  public Optional<Section> getById(Integer id) {
    return sectionReadRepository.findById(id)
        .map(sectionRedisMapper::toDomain);
  }

  @Override
  public List<Section> getAllByIds(List<Integer> ids) {
  return sectionReadRepository.findAllById(ids)
        .stream()
        .map(sectionRedisMapper::toDomain)
        .toList();
  }

}
