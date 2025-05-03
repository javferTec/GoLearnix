package com.golearnix.ports.output.query;

import com.golearnix.domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionQueryRepositoryPort {

  Optional<Section> getById(Integer id);
  List<Section> getAllByIds(List<Integer> ids);

}
