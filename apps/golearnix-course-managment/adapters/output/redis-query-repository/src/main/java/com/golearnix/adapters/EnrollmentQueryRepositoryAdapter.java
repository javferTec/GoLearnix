package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.User;
import com.golearnix.ports.output.query.EnrollmentQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class EnrollmentQueryRepositoryAdapter implements EnrollmentQueryRepositoryPort {

  @Override
  public List<Enrollment> getAllByIds(List<Integer> ids) {
    return List.of(new Enrollment(1, new User(UUID.randomUUID())));
  }

}
