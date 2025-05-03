package com.golearnix.ports.input;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Category;

public interface CategoryServicePort {

  Category getById(Integer id) throws ResourceNotFoundException;

}
