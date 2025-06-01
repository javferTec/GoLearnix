package com.golearnix;

import com.golearnix.common.annotations.Util;
import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Review;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;
import com.golearnix.jpa.mappers.specific.CategoryJpaMapper;
import com.golearnix.jpa.mappers.specific.CourseJpaMapper;
import com.golearnix.jpa.mappers.specific.EnrollmentJpaMapper;
import com.golearnix.jpa.mappers.specific.LessonJpaMapper;
import com.golearnix.jpa.mappers.specific.ProgressJpaMapper;
import com.golearnix.jpa.mappers.specific.ReviewJpaMapper;
import com.golearnix.jpa.mappers.specific.SectionJpaMapper;
import com.golearnix.jpa.mappers.specific.UserJpaMapper;
import com.golearnix.jpa.repositories.CategoryEntityJpaRepository;
import com.golearnix.jpa.repositories.CourseEntityJpaRepository;
import com.golearnix.jpa.repositories.EnrollmentEntityJpaRepository;
import com.golearnix.jpa.repositories.LessonEntityJpaRepository;
import com.golearnix.jpa.repositories.ProgressEntityJpaRepository;
import com.golearnix.jpa.repositories.ReviewEntityJpaRepository;
import com.golearnix.jpa.repositories.SectionEntityJpaRepository;
import com.golearnix.jpa.repositories.UserEntityJpaRepository;
import com.golearnix.redis.mappers.specific.CategoryRedisMapper;
import com.golearnix.redis.mappers.specific.CourseRedisMapper;
import com.golearnix.redis.mappers.specific.EnrollmentRedisMapper;
import com.golearnix.redis.mappers.specific.LessonRedisMapper;
import com.golearnix.redis.mappers.specific.ProgressRedisMapper;
import com.golearnix.redis.mappers.specific.ReviewRedisMapper;
import com.golearnix.redis.mappers.specific.SectionRedisMapper;
import com.golearnix.redis.mappers.specific.UserRedisMapper;
import com.golearnix.redis.repositories.CategoryReadRepository;
import com.golearnix.redis.repositories.CourseReadRepository;
import com.golearnix.redis.repositories.EnrollmentReadRepository;
import com.golearnix.redis.repositories.LessonReadRepository;
import com.golearnix.redis.repositories.ProgressReadRepository;
import com.golearnix.redis.repositories.ReviewReadRepository;
import com.golearnix.redis.repositories.SectionReadRepository;
import com.golearnix.redis.repositories.UserReadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Util
@RequiredArgsConstructor
public class CreateRedisData {

  private final CategoryEntityJpaRepository categoryEntityJpaRepository;
  private final CourseEntityJpaRepository courseEntityJpaRepository;
  private final EnrollmentEntityJpaRepository enrollmentEntityJpaRepository;
  private final LessonEntityJpaRepository lessonEntityJpaRepository;
  private final ProgressEntityJpaRepository progressEntityJpaRepository;
  private final ReviewEntityJpaRepository reviewEntityJpaRepository;
  private final SectionEntityJpaRepository sectionEntityJpaRepository;
  private final UserEntityJpaRepository userEntityJpaRepository;

  private final CategoryReadRepository categoryReadRepository;
  private final CourseReadRepository courseReadRepository;
  private final EnrollmentReadRepository enrollmentReadRepository;
  private final LessonReadRepository lessonReadRepository;
  private final ProgressReadRepository progressReadRepository;
  private final ReviewReadRepository reviewReadRepository;
  private final SectionReadRepository sectionReadRepository;
  private final UserReadRepository userReadRepository;

  private final CategoryJpaMapper categoryJpaMapper;
  private final CourseJpaMapper courseJpaMapper;
  private final EnrollmentJpaMapper enrollmentJpaMapper;
  private final LessonJpaMapper lessonJpaMapper;
  private final ProgressJpaMapper progressJpaMapper;
  private final ReviewJpaMapper reviewJpaMapper;
  private final SectionJpaMapper sectionJpaMapper;
  private final UserJpaMapper userJpaMapper;

  private final CategoryRedisMapper categoryRedisMapper;
  private final CourseRedisMapper courseRedisMapper;
  private final EnrollmentRedisMapper enrollmentRedisMapper;
  private final LessonRedisMapper lessonRedisMapper;
  private final ProgressRedisMapper progressRedisMapper;
  private final ReviewRedisMapper reviewRedisMapper;
  private final SectionRedisMapper sectionRedisMapper;
  private final UserRedisMapper userRedisMapper;

  @Transactional
  public void createAll() {
    DataHolder data = loadDomainData();
    saveToRedis(data);
  }

  private DataHolder loadDomainData() {

    List<Category> categories = categoryEntityJpaRepository.findAll().stream().map(categoryJpaMapper::toDomain).toList();
    List<Course> courses = courseEntityJpaRepository.findAll().stream().map(courseJpaMapper::toDomain).toList();
    List<Enrollment> enrollments = enrollmentEntityJpaRepository.findAll().stream().map(enrollmentJpaMapper::toDomain).toList();
    List<Lesson> lessons = lessonEntityJpaRepository.findAll().stream().map(lessonJpaMapper::toDomain).toList();
    List<Progress> progresses = progressEntityJpaRepository.findAll().stream().map(progressJpaMapper::toDomain).toList();
    List<Review> reviews = reviewEntityJpaRepository.findAll().stream().map(reviewJpaMapper::toDomain).toList();
    List<Section> sections = sectionEntityJpaRepository.findAll().stream().map(sectionJpaMapper::toDomain).toList();
    List<User> users = userEntityJpaRepository.findAll().stream().map(userJpaMapper::toDomain).toList();

    return new DataHolder(categories, courses, enrollments, lessons, progresses, reviews, sections, users);

  }

  private void saveToRedis(DataHolder data) {

    categoryReadRepository.saveAll(data.categories().stream().map(categoryRedisMapper::toReadModel).collect(Collectors.toList()));
    courseReadRepository.saveAll(data.courses().stream().map(courseRedisMapper::toReadModel).collect(Collectors.toList()));
    enrollmentReadRepository.saveAll(data.enrollments().stream().map(enrollmentRedisMapper::toReadModel).collect(Collectors.toList()));
    lessonReadRepository.saveAll(data.lessons().stream().map(lessonRedisMapper::toReadModel).collect(Collectors.toList()));
    progressReadRepository.saveAll(data.progresses().stream().map(progressRedisMapper::toReadModel).collect(Collectors.toList()));
    reviewReadRepository.saveAll(data.reviews().stream().map(reviewRedisMapper::toReadModel).collect(Collectors.toList()));
    sectionReadRepository.saveAll(data.sections().stream().map(sectionRedisMapper::toReadModel).collect(Collectors.toList()));
    userReadRepository.saveAll(data.users().stream().map(userRedisMapper::toReadModel).collect(Collectors.toList()));

  }

}
