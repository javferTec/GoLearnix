package com.golearnix.services;

import com.golearnix.common.annotations.DomainService;
import com.golearnix.domain.Enrollment;
import com.golearnix.ports.input.EnrollmentServicePort;
import com.golearnix.ports.output.query.EnrollmentQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class EnrollmentServiceUseCase implements EnrollmentServicePort {

    private final EnrollmentQueryRepositoryPort enrollmentRepository;

    @Override
    public List<Enrollment> getAllByIds(List<Integer> ids) {
        return enrollmentRepository.getAllByIds(ids);
    }

}
