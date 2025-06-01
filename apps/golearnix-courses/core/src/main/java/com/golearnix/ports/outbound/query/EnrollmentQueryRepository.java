package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Enrollment;

import java.util.List;

public interface EnrollmentQueryRepository {

  List<Enrollment> getAllByIds(List<Integer> ids);

}
