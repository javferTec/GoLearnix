package com.golearnix.ports.output.query;

import com.golearnix.domain.Enrollment;

import java.util.List;

public interface EnrollmentQueryRepositoryPort {

  List<Enrollment> getAllByIds(List<Integer> ids);

}
