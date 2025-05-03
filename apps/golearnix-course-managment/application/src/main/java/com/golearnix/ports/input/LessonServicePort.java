package com.golearnix.ports.input;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Lesson;

public interface LessonServicePort {

  Lesson getById(Integer id) throws ResourceNotFoundException;

}
