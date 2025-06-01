package com.golearnix.events.listeners;

import com.golearnix.common.annotations.Listener;
import com.golearnix.domain.Course;
import com.golearnix.events.CourseCreatedEvent;
import com.golearnix.events.CourseDeletedEvent;
import com.golearnix.events.CourseUpdatedEvent;
import com.golearnix.ports.outbound.command.CourseCommandRepository;
import com.golearnix.ports.outbound.query.CourseQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Listener
@RequiredArgsConstructor
@Slf4j
public class CourseEventListener {

  private final CourseQueryRepository courseQueryRepositoryPort;
  private final CourseCommandRepository courseCommandRepositoryPort;

  @EventListener
  public void onCourseCreated(CourseCreatedEvent event) {

    Course course = event.course();

    try {
      courseQueryRepositoryPort.save(course);
    } catch (Exception redisEx) {
      courseCommandRepositoryPort.delete(course.getId());
    }

  }

  @EventListener
  public void onCourseUpdated(CourseUpdatedEvent event) {

    Course oldCourse = event.oldCourse();
    Course newCourse = event.newCourse();

    try {
      courseQueryRepositoryPort.update(newCourse);
    } catch (Exception redisEx) {
      courseCommandRepositoryPort.save(oldCourse);
    }

  }

  @EventListener
  public void onCourseDeleted(CourseDeletedEvent event) {

    Course oldCourse = event.oldCourse();

    try {
      courseQueryRepositoryPort.delete(oldCourse.getId());
    } catch (Exception redisEx) {
      courseCommandRepositoryPort.save(oldCourse);
    }

  }

}
