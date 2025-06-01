package com.golearnix.jpa;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Course;
import com.golearnix.jpa.mappers.specific.CourseJpaMapper;
import com.golearnix.jpa.repositories.CourseEntityJpaRepository;
import com.golearnix.ports.outbound.command.CourseCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class CourseCommandRepositoryImpl implements CourseCommandRepository {

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
