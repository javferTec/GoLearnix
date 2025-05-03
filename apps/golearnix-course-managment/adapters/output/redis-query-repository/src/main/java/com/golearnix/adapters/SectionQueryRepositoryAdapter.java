package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.ports.output.query.SectionQueryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class SectionQueryRepositoryAdapter implements SectionQueryRepositoryPort {

  @Override
  public Optional<Section> getById(Integer id) {
    return Optional.of(new Section(1, "Section 1", 1, List.of(new Lesson(1, "Lesson 1", "url", 234, 1, List.of(new Progress(1, new User(
        UUID.randomUUID()), true))))));
  }

  @Override
  public List<Section> getAllByIds(List<Integer> ids) {
    return List.of(new Section(1, "Section 1", 1, List.of(new Lesson(1, "Lesson 1", "url", 234, 1, List.of(new Progress(1, new User(
        UUID.randomUUID()), true))))));

  }

}
