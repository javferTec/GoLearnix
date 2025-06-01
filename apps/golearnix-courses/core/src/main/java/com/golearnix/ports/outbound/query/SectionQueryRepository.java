package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Section;

import java.util.List;
import java.util.Optional;

public interface SectionQueryRepository {

  Optional<Section> getById(Integer id);
  List<Section> getAllByIds(List<Integer> ids);

}
