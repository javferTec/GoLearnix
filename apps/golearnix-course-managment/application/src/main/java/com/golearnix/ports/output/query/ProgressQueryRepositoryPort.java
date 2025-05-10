package com.golearnix.ports.output.query;

import com.golearnix.domain.Progress;

import java.util.Optional;

public interface ProgressQueryRepositoryPort {

  Optional<Progress> getById(Integer id);

}
