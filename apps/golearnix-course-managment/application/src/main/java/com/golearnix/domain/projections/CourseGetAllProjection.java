package com.golearnix.domain.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CourseGetAllProjection {

  @Value("#{target.title}")
  String getTitle();

  @Value("#{target.description}")
  String getDescription();

}
