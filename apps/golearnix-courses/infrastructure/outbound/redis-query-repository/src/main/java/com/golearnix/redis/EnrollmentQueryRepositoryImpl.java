package com.golearnix.redis;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Enrollment;
import com.golearnix.ports.outbound.query.EnrollmentQueryRepository;
import com.golearnix.redis.mappers.specific.EnrollmentRedisMapper;
import com.golearnix.redis.repositories.EnrollmentReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class EnrollmentQueryRepositoryImpl implements EnrollmentQueryRepository {

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
