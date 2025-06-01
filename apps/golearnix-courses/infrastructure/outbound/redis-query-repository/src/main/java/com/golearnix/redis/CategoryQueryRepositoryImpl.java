package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Category;
import com.golearnix.ports.outbound.query.CategoryQueryRepository;
import com.golearnix.redis.mappers.specific.CategoryRedisMapper;
import com.golearnix.redis.repositories.CategoryReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class CategoryQueryRepositoryImpl implements CategoryQueryRepository {

  private final CategoryReadRepository categoryReadRepository;
  private final CategoryRedisMapper categoryRedisMapper;

  @Override
  public Optional<Category> getById(Integer id) {
    return categoryReadRepository.findById(id)
        .map(categoryRedisMapper::toDomain);
  }

}
