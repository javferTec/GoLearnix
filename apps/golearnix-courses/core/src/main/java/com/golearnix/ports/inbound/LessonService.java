package com.golearnix.ports.inbound;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Lesson;

public interface LessonService {

  Lesson getById(Integer id) throws ResourceNotFoundException;

}
