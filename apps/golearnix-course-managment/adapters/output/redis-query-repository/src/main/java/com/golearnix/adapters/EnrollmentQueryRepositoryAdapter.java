package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.User;
import com.golearnix.mappers.specific.EnrollmentRedisMapper;
import com.golearnix.ports.output.query.EnrollmentQueryRepositoryPort;
import com.golearnix.repositories.EnrollmentReadRepository;
import com.golearnix.repositories.UserReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RepositoryAdapter
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
