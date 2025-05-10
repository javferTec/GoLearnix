package com.golearnix;

import com.golearnix.domain.Category;
import com.golearnix.domain.Course;
import com.golearnix.domain.Enrollment;
import com.golearnix.domain.Lesson;
import com.golearnix.domain.Progress;
import com.golearnix.domain.Review;
import com.golearnix.domain.Section;
import com.golearnix.domain.User;

import java.util.List;

public record DataHolder(
    List<Category> categories,
    List<Course> courses,
    List<Enrollment> enrollments,
    List<Lesson> lessons,
    List<Progress> progresses,
    List<Review> reviews,
    List<Section> sections,
    List<User> users
) {

}
