package com.golearnix.events;

import com.golearnix.domain.Course;


public record CourseDeletedEvent(Course oldCourse) {

}
