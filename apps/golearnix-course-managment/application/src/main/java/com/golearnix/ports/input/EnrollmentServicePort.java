package com.golearnix.ports.input;

import com.golearnix.domain.Enrollment;

import java.util.List;

public interface EnrollmentServicePort {

  List<Enrollment> getAllByIds(List<Integer> ids);

}
