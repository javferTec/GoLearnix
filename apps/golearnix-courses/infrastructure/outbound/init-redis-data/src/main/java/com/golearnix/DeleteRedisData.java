package com.golearnix;

import com.golearnix.common.annotations.Util;
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

@Util
@RequiredArgsConstructor
public class DeleteRedisData {

  private final CategoryReadRepository categoryReadRepository;
  private final CourseReadRepository courseReadRepository;
  private final EnrollmentReadRepository enrollmentReadRepository;
  private final LessonReadRepository lessonReadRepository;
  private final ProgressReadRepository progressReadRepository;
  private final ReviewReadRepository reviewReadRepository;
  private final SectionReadRepository sectionReadRepository;
  private final UserReadRepository userReadRepository;

  @Transactional
  public void deleteAll() {

    categoryReadRepository.deleteAll();
    courseReadRepository.deleteAll();
    enrollmentReadRepository.deleteAll();
    lessonReadRepository.deleteAll();
    progressReadRepository.deleteAll();
    reviewReadRepository.deleteAll();
    sectionReadRepository.deleteAll();
    userReadRepository.deleteAll();

  }

}

