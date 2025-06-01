package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Section;
import com.golearnix.ports.outbound.query.SectionQueryRepository;
import com.golearnix.redis.mappers.specific.SectionRedisMapper;
import com.golearnix.redis.repositories.SectionReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class SectionQueryRepositoryImpl implements SectionQueryRepository {

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
