package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.domain.Enrollment;
import com.golearnix.ports.inbound.EnrollmentService;
import com.golearnix.ports.outbound.query.EnrollmentQueryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentQueryRepository enrollmentRepository;

    @Override
    public List<Enrollment> getAllByIds(List<Integer> ids) {
        return enrollmentRepository.getAllByIds(ids);
    }

}
