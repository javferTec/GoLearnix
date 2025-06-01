package com.golearnix.ports.inbound;

import com.golearnix.common.exceptions.ResourceNotFoundException;
import com.golearnix.domain.Category;

public interface CategoryService {

  Category getById(Integer id) throws ResourceNotFoundException;

}
