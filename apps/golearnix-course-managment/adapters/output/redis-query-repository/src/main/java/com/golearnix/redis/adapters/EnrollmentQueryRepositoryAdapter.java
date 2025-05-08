package com.golearnix.redis.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Enrollment;
import com.golearnix.ports.output.query.EnrollmentQueryRepositoryPort;
import com.golearnix.redis.mappers.specific.EnrollmentRedisMapper;
import com.golearnix.redis.repositories.EnrollmentReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class EnrollmentQueryRepositoryAdapter implements EnrollmentQueryRepositoryPort {

  private final EnrollmentReadRepository enrollmentReadRepository;
  private final EnrollmentRedisMapper enrollmentRedisMapper;

  @Override
  public List<Enrollment> getAllByIds(List<Integer> ids) {
    return enrollmentReadRepository.findAllById(ids)
        .stream()
        .map(enrollmentRedisMapper::toDomain)
        .toList();
  }

}
