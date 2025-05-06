package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Course;
import com.golearnix.mappers.specific.CourseJpaMapper;
import com.golearnix.ports.output.command.CourseCommandRepositoryPort;
import com.golearnix.repositories.CourseEntityJpaRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
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

  @Override
  public void enroll(Course course) {
    courseEntityJpaRepository.save(courseJpaMapper.toJpaEntity(course));
  }

  @Override
  public void completeLesson(Course course) {
    courseEntityJpaRepository.save(courseJpaMapper.toJpaEntity(course));
  }

}
