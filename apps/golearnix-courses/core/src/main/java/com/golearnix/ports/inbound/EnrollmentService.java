package com.golearnix.ports.inbound;

import com.golearnix.domain.Enrollment;

import java.util.List;

public interface EnrollmentService {

  List<Enrollment> getAllByIds(List<Integer> ids);

}
