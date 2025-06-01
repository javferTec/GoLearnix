package com.golearnix.ports.outbound.query;

import com.golearnix.domain.Category;

import java.util.Optional;

public interface CategoryQueryRepository {

  Optional<Category> getById(Integer id);

}
