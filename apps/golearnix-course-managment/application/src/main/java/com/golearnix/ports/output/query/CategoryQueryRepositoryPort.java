package com.golearnix.ports.output.query;

import com.golearnix.domain.Category;

import java.util.Optional;

public interface CategoryQueryRepositoryPort {

  Optional<Category> getById(Integer id);

}
