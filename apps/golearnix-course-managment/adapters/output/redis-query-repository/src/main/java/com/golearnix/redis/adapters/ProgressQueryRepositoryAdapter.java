package com.golearnix.redis.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Progress;
import com.golearnix.ports.output.query.ProgressQueryRepositoryPort;
import com.golearnix.redis.mappers.specific.ProgressRedisMapper;
import com.golearnix.redis.repositories.ProgressReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RepositoryAdapter
@RequiredArgsConstructor
public class ProgressQueryRepositoryAdapter implements ProgressQueryRepositoryPort {

  private final ProgressReadRepository progressReadRepository;
  private final ProgressRedisMapper progressRedisMapper;

  @Override
  public Optional<Progress> getById(Integer id) {
    return progressReadRepository.findById(id)
        .map(progressRedisMapper::toDomain);
  }

}
