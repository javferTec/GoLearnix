package com.golearnix.ports.input;

import com.golearnix.domain.Section;

import java.util.List;

public interface SectionServicePort {

  Section getById(Integer id);
  List<Section> getAllByIds(List<Integer> ids);

}
