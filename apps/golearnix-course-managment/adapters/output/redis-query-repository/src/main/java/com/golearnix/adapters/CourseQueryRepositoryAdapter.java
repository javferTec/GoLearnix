package com.golearnix.adapters;

import com.golearnix.common.annotations.RepositoryAdapter;
import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Review;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.domain.projections.CourseGetAllProjection;
import com.golearnix.mappers.specific.CourseRedisMapper;
import com.golearnix.ports.output.query.CourseQueryRepositoryPort;
import com.golearnix.repositories.CourseReadRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RepositoryAdapter
@RequiredArgsConstructor
public class CourseQueryRepositoryAdapter implements CourseQueryRepositoryPort{

  private final CourseReadRepository courseReadRepository;
  private final CourseRedisMapper courseRedisMapper;

  @Override
  public List<CourseGetAllProjection> getAll() {
    return courseReadRepository.findAllProjected();
  }

  @Override
  public Optional<Course> getById(Integer id) {
    return courseReadRepository.findById(id)
        .map(courseRedisMapper::toDomain);
  }

}
