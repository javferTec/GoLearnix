package com.golearnix.events;

import com.golearnix.domain.Course;

public record CourseUpdatedEvent(Course oldCourse, Course newCourse) {

}
