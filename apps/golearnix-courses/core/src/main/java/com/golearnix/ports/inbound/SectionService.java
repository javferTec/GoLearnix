package com.golearnix.ports.inbound;

import com.golearnix.domain.Section;

import java.util.List;

public interface SectionService {

  Section getById(Integer id);
  List<Section> getAllByIds(List<Integer> ids);

}
