package com.golearnix.jpa.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Course;
import com.golearnix.jpa.mappers.specific.CourseJpaMapper;
import com.golearnix.jpa.repositories.CourseEntityJpaRepository;
import com.golearnix.ports.output.command.CourseCommandRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class CourseCommandRepositoryAdapter implements CourseCommandRepositoryPort {

  private final CourseEntityJpaRepository courseEntityJpaRepository;
  private final CourseJpaMapper courseJpaMapper;

  @Override
  public void save(Course course) {
    courseEntityJpaRepository.save(courseJpaMapper.toJpaEntity(course));
  }

  @Override
  public void delete(Integer id) {
    courseEntityJpaRepository.deleteById(id);
  }

}
