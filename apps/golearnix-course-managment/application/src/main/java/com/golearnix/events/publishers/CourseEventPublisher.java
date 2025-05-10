package com.golearnix.events.publishers;

import com.golearnix.common.annotations.Publisher;
import com.golearnix.domain.Course;
import com.golearnix.events.CourseCreatedEvent;
import com.golearnix.events.CourseDeletedEvent;
import com.golearnix.events.CourseUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Publisher
@RequiredArgsConstructor
public class CourseEventPublisher {

  private final ApplicationEventPublisher publisher;

  public void publishCourseCreated(Course course) {
    publisher.publishEvent(new CourseCreatedEvent(course));
  }

  public void publishCourseUpdated(Course oldCourse, Course newCourse) {
    publisher.publishEvent(new CourseUpdatedEvent(oldCourse, newCourse));
  }

  public void publishCourseDeleted(Course oldCourse) {
    publisher.publishEvent(new CourseDeletedEvent(oldCourse));
  }

}
