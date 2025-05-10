package com.golearnix.redis.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Course;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.ports.output.query.CourseQueryRepositoryPort;
import com.golearnix.redis.mappers.specific.CourseRedisMapper;
import com.golearnix.redis.repositories.CourseReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RepositoryAdapter
@Transactional
@RequiredArgsConstructor
public class CourseQueryRepositoryAdapter implements CourseQueryRepositoryPort{

  private final CourseReadRepository courseReadRepository;
  private final CourseRedisMapper courseRedisMapper;

  @Override
  public List<CourseGetAllProjection> getAll() {
    return courseReadRepository.findAllBy();
  }

  @Override
  public Optional<Course> getById(Integer id) {
    return courseReadRepository.findById(id)
        .map(courseRedisMapper::toDomain);
  }

  @Override
  public void save(Course course) {
    courseReadRepository.save(courseRedisMapper.toReadModel(course));
  }

  @Override
  public void update(Course course) {
    courseReadRepository.update(courseRedisMapper.toReadModel(course));
  }

  @Override
  public void delete(Integer id) {
    courseReadRepository.deleteById(id);
  }

}
