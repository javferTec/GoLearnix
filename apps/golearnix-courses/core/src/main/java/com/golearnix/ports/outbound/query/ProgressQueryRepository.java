package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Progress;

import java.util.Optional;

public interface ProgressQueryRepository {

  Optional<Progress> getById(Integer id);

}
